package com.tshirtShop.serverSide.publicAPIs.repository;

import com.tshirtShop.serverSide.publicAPIs.POJO.CoordinateDataHolder;
import com.tshirtShop.serverSide.publicAPIs.POJO.SellerOrderInsights;
import com.tshirtShop.serverSide.publicAPIs.entity.CustomerOrder;
import com.tshirtShop.serverSide.publicAPIs.entity.CustomerOrderCart;
import com.tshirtShop.serverSide.publicAPIs.entity.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

    public List<SellerOrderInsights> getAllOrdersInsightsSellerTimeWise(String username, String saleDuration) {
        Query query = entityManager.createQuery("Select sum(co.totalCost) as totalCost, DATE_TRUNC('"+saleDuration+"', co.orderDate) as orderDate from CustomerOrder co where co.productList.username = :username group by DATE_TRUNC('"+saleDuration+"', co.orderDate)");
        query.setParameter("username", username);
        List<Object[]> customerOrderList = query.getResultList();
        List<SellerOrderInsights> sellerOrderInsightsList = new ArrayList<>();
        for (Object[] queryObject1 : customerOrderList) {
            sellerOrderInsightsList.add(new SellerOrderInsights((Date)queryObject1[1], (Long)queryObject1[0]));
        }
        return sellerOrderInsightsList;
    }

    public List<CoordinateDataHolder> getAllOrdersInsightsSellerCategoryWise(String username) {
        Query query = entityManager.createQuery("Select sum(co.totalCost) as totalCost, pl.productCategory as productCategory from CustomerOrder co inner join ProductList pl on co.productList.productCategory = pl.productCategory where co.productList.username = :username group by pl.productCategory");
        query.setParameter("username", username);
        List<Object[]> customerOrderList = query.getResultList();
        List<CoordinateDataHolder> coordinateDataHolderList = new ArrayList<>();
        for (Object[] queryObject1 : customerOrderList) {
            coordinateDataHolderList.add(new CoordinateDataHolder(queryObject1[1].toString(), (Long)queryObject1[0]));
        }
        return coordinateDataHolderList;
    }

    public void cancelOrder(Long orderId) {
        Query query = entityManager.createQuery("Delete from CustomerOrder co where co.orderId = :orderId");
        query.setParameter("orderId", orderId);
        query.executeUpdate();
    }

}
