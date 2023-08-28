package com.tshirtShop.serverSide.publicAPIs.repository;

import com.tshirtShop.serverSide.publicAPIs.POJO.SellerOrderInsights;
import com.tshirtShop.serverSide.publicAPIs.entity.CustomerOrder;
import com.tshirtShop.serverSide.publicAPIs.entity.CustomerOrderCart;
import com.tshirtShop.serverSide.publicAPIs.entity.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class OrderRepo {

    @Autowired
    EntityManager entityManager;

    public void addProductToCart(CustomerOrderCart customerOrderCart) {
        entityManager.persist(customerOrderCart);
    }

    public List<CustomerOrderCart> fetchAllProductsInCart(String customerName) {
        Query query = entityManager.createQuery("Select orl from CustomerOrderCart orl where orl.customerName = :customerName");
        query.setParameter("customerName", customerName);
        List<CustomerOrderCart> customerOrderCartList = query.getResultList();
        return  customerOrderCartList;
    }

    public List<ProductList> fetchCartProductsDetails(List<Long> productIdList) {
        Query query = entityManager.createQuery("Select pl from ProductList pl where pl.productId in :productIdList", ProductList.class);
        query.setParameter("productIdList", productIdList);
        List<ProductList> productList = query.getResultList();
        return productList;
    }

    public byte[] fetchProductThumbnail(Long productId) {
        Query query = entityManager.createQuery("Select pl from ProductList pl where pl.productId = :productId", ProductList.class);
        query.setParameter("productId", productId);
        List<ProductList> productLists = query.getResultList();
        return productLists.get(0).getThumbNailImage();
    }

    public void deleteProductFromCart(Long productId) {
        Query query = entityManager.createQuery("Delete from CustomerOrderCart coc where coc.productId = :productId");
        query.setParameter("productId", productId);
        query.executeUpdate();
        entityManager.flush();
    }

    public void placeOrder(List<CustomerOrder> customerOrders) {
        for(CustomerOrder customerOrder : customerOrders) {
            entityManager.persist(customerOrder);
        }
        entityManager.flush();
    }

    public void clearCart(String username) {
        Query query = entityManager.createQuery("Delete from CustomerOrderCart coc where coc.customerName = :username");
        query.setParameter("username", username);
        query.executeUpdate();
    }

    public List<CustomerOrder> getAllMyOrders(String username) {
        Query query = entityManager.createQuery("Select co from CustomerOrder co where co.buyer = :username");
        query.setParameter("username", username);
        List<CustomerOrder> customerOrderList = query.getResultList();
        return customerOrderList;
    }

    public List<CustomerOrder> getAllOrdersInsightsSeller(String username) {
        Query query = entityManager.createQuery("Select co from CustomerOrder co where co.productList.username = :username order by co.orderDate", CustomerOrder.class);
        query.setParameter("username", username);
        List<CustomerOrder> customerOrderList = query.getResultList();
        return customerOrderList;
    }

    public void cancelOrder(Long orderId) {
        Query query = entityManager.createQuery("Delete from CustomerOrder co where co.orderId = :orderId");
        query.setParameter("orderId", orderId);
        query.executeUpdate();
    }

}
