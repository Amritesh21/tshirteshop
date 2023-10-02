package com.tshirtShop.serverSide.publicAPIs.controller;

import com.tshirtShop.serverSide.publicAPIs.DTO.*;
import com.tshirtShop.serverSide.publicAPIs.POJO.SellerOrderInsights;
import com.tshirtShop.serverSide.publicAPIs.entity.CustomerOrder;
import com.tshirtShop.serverSide.publicAPIs.entity.CustomerOrderCart;
import com.tshirtShop.serverSide.publicAPIs.entity.ProductList;
import com.tshirtShop.serverSide.publicAPIs.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class OrderController {

    @Autowired
    OrderRepo orderRepo;

    @PostMapping("/api/auth/buyer/add/{productId}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long productId, @PathParam("color") String color,
                                                   @PathParam("size") String size,
                                                   @PathParam("quantity") Long quantity
    ) {
        String customerName = SecurityContextHolder.getContext().getAuthentication().getName();
        CustomerOrderCart customerOrderCart = new CustomerOrderCart(productId, customerName, color, size, quantity);
        orderRepo.addProductToCart(customerOrderCart);
        return ResponseEntity.ok().body("Product successfully added to cart");
    }

    @GetMapping("/api/auth/buyer/get/cart/products")
    public List<CartProductDTO> getAllProductsInCart() {
        final List<CustomerOrderCart> customerOrderCartList = orderRepo.fetchAllProductsInCart(SecurityContextHolder.getContext().getAuthentication().getName());
        final List<ProductList> fetchedProductList = orderRepo.fetchCartProductsDetails(customerOrderCartList.stream().map(x -> x.getProductId()).collect(Collectors.toList()));
        List<NewProductDTO> getProductDTO = fetchedProductList.stream().map(p -> {
            NewProductDTO newProductDTO = new NewProductDTO();
            newProductDTO.setProductId(p.getProductId());
            newProductDTO.setProductCategory(p.getProductCategory());
            newProductDTO.setProductDescription(p.getProductDescription());
            newProductDTO.setProductPrice(p.getProductPrice());
            newProductDTO.setSelectSizes(p.getSizes());
            newProductDTO.setColorsArray(p.getColors());
            newProductDTO.setTotalStock(p.getTotalStockPresent());
            return newProductDTO;
        }).collect(Collectors.toList());
        Function<NewProductDTO, Long> keyMapperFunc = (x) -> { return x.getProductId(); };
        Function<NewProductDTO, NewProductDTO> valueMapperFunc = (x) -> { return x; };
        Map<Long, NewProductDTO> productListMap = getProductDTO.stream().collect(Collectors.toMap(keyMapperFunc, valueMapperFunc));
        List<CartProductDTO> cartProductDTOList = new ArrayList<>();
        for(CustomerOrderCart customerOrderCart : customerOrderCartList) {
            cartProductDTOList.add(new CartProductDTO(productListMap.get(customerOrderCart.getProductId()), customerOrderCart));
        }
        return cartProductDTOList;
    }

    @GetMapping(value = "/api/public/buyer/get/{productId}/thumbnail", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getProductThumbnail(@PathVariable Long productId) {
        return orderRepo.fetchProductThumbnail(productId);
    }

    @PutMapping(value = "/api/auth/buyer/remove/{productId}/from/cart")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Long productId) {
        orderRepo.deleteProductFromCart(productId);
        return ResponseEntity.ok().body("Product removed from cart");
    }

    @PostMapping("/api/auth/buyer/place/order")
    public ResponseEntity<String> placeOrder(@RequestBody List<PlaceOrderDTO> placeOrderDTOList) {
        List<ProductList> productLists = orderRepo.fetchCartProductsDetails(placeOrderDTOList.stream().map(x -> x.getProductId()).collect(Collectors.toList()));
        Function<ProductList, Long> keyMapper = (x) -> x.getProductId();
        Function<ProductList, ProductList> valueMapper = (x) -> x;
        Map<Long,ProductList> productListMap = productLists.stream().collect(Collectors.toMap(keyMapper, valueMapper));
        List<CustomerOrder> customerOrderLists = placeOrderDTOList.stream().map(x -> {
            CustomerOrder customerOrder = new CustomerOrder();
            customerOrder.setBuyer(SecurityContextHolder.getContext().getAuthentication().getName());
            customerOrder.setColor(x.getColor());
            customerOrder.setProductList(productListMap.get(x.getProductId()));
            customerOrder.setQuantity(x.getQuantity());
            customerOrder.setPaymentMethod(x.getPaymentMethod());
            customerOrder.setSize(x.getSize());
            customerOrder.setTotalCost(productListMap.get(x.getProductId()).getProductPrice() * x.getQuantity());
            customerOrder.setAddress(x.getAddress());
            customerOrder.setPhno(x.getPhno());
            customerOrder.setOrderDate();
            customerOrder.setOrderStatus();
            return customerOrder;
        }).collect(Collectors.toList());
        orderRepo.placeOrder(customerOrderLists);
        orderRepo.clearCart(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.ok().body("Order placed successfully");
    }

    @GetMapping("/api/auth/buyer/get/all/my/orders")
    public List<GetOrderDTO> getAllCustomerOrders() {
        List<CustomerOrder> customerOrderList = orderRepo.getAllMyOrders(SecurityContextHolder.getContext().getAuthentication().getName());
        List<GetOrderDTO> getOrderDTOList = customerOrderList.stream().map(x -> {
            GetOrderDTO getOrderDTO = new GetOrderDTO();
            getOrderDTO.setAddress(x.getAddress());
            getOrderDTO.setColor(x.getColor());
            getOrderDTO.setPhno(x.getPhno());
            getOrderDTO.setPaymentMethod(x.getPaymentMethod());
            getOrderDTO.setThumbNail(Base64.getEncoder().encodeToString(x.getProductList().getThumbNailImage()));
            getOrderDTO.setQuantity(x.getQuantity());
            getOrderDTO.setSize(x.getSize());
            getOrderDTO.setProductId(x.getProductList().getProductId());
            getOrderDTO.setTotalCost(x.getTotalCost());
            getOrderDTO.setOrderStatus(x.getOrderStatus());
            getOrderDTO.setProductCategory(x.getProductList().getProductCategory());
            getOrderDTO.setOrderId(x.getOrderId());
            return getOrderDTO;
        }).collect(Collectors.toList());
        return getOrderDTOList;
    }

    @DeleteMapping("/api/auth/buyer/order/cancel")
    public void deleteOrder(@PathParam("orderId") Long orderId) {
        orderRepo.cancelOrder(orderId);
    }

//    @GetMapping("/api/public/order/insights")
//    public SellerOrderInsightDTO orderInsights() {
//        List<CustomerOrder> customerOrderList = orderRepo.getAllOrdersInsightsSeller("sellerUser1");
//        List<SellerOrderInsights> sellerOrderInsightsList = customerOrderList.stream().map(x -> {
//            SellerOrderInsights sellerOrderInsights =  new SellerOrderInsights();
//            sellerOrderInsights.setOrderDate(x.getOrderDate());
//            sellerOrderInsights.setTotalCost(x.getTotalCost());
//            return sellerOrderInsights;
//        }).collect(Collectors.toList());
//        SellerOrderInsightDTO sellerOrderInsightDTO = new SellerOrderInsightDTO();
//        sellerOrderInsightDTO.setDataType("STRING", "NUMBER");
//        sellerOrderInsightDTO.setData(sellerOrderInsightsList);
//        sellerOrderInsightDTO.setLegend("Order Date", "Total Earning");
//        sellerOrderInsightDTO.setGridLineFrequency(10, 10);
//
//        return sellerOrderInsightDTO;
//    }

    @GetMapping("/api/public/redirect")
    public void redirectToURL(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("/login");
    }

}
