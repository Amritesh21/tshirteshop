package com.tshirtShop.serverSide.publicAPIs.controller;


import com.tshirtShop.serverSide.publicAPIs.DTO.AllShopOrdersDTO;
import com.tshirtShop.serverSide.publicAPIs.DTO.GraphDataDTO;
import com.tshirtShop.serverSide.publicAPIs.DTO.InsightDataDTO;
import com.tshirtShop.serverSide.publicAPIs.DTO.SellerOrderInsightDTO;
import com.tshirtShop.serverSide.publicAPIs.POJO.CoordinateDataHolder;
import com.tshirtShop.serverSide.publicAPIs.POJO.SellerOrderInsights;
import com.tshirtShop.serverSide.publicAPIs.entity.CustomerOrder;
import com.tshirtShop.serverSide.publicAPIs.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ShopInsightsController {

    @Autowired
    OrderRepo orderRepo;

    @GetMapping("/api/auth/seller/getAllOrders")
    public List<AllShopOrdersDTO> getAllOrders() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<CustomerOrder> customerOrderList = orderRepo.getAllOrdersInsightsSeller(userName);
        List<AllShopOrdersDTO> allShopOrdersDTOList = new ArrayList<>();
        customerOrderList.stream().forEach(x -> {
            AllShopOrdersDTO allShopOrdersDTO = new AllShopOrdersDTO();
            allShopOrdersDTO.setOrderId(x.getOrderId());
            allShopOrdersDTO.setOrderStatus(x.getOrderStatus());
            allShopOrdersDTO.setProductCategory(x.getProductList().getProductCategory());
            allShopOrdersDTO.setTotalCost(x.getTotalCost());
            allShopOrdersDTO.setOrderDate(x.getOrderDate());
            allShopOrdersDTOList.add(allShopOrdersDTO);
        });
        return allShopOrdersDTOList;
    }

    @GetMapping("/api/auth/seller/getSales/{saleDuration}")
    public SellerOrderInsightDTO getValue(@PathVariable String saleDuration) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<SellerOrderInsights> customerOrderList = orderRepo.getAllOrdersInsightsSellerTimeWise(userName, saleDuration);
        List<SellerOrderInsights> sellerOrderInsightsList = customerOrderList.stream().map(x -> {
            SellerOrderInsights sellerOrderInsights =  new SellerOrderInsights();
            sellerOrderInsights.setOrderDate(x.getOrderDate());
            sellerOrderInsights.setTotalCost(x.getTotalCost());
            return sellerOrderInsights;
        }).collect(Collectors.toList());
        SellerOrderInsightDTO sellerOrderInsightDTO = new SellerOrderInsightDTO();
        sellerOrderInsightDTO.setDataType("STRING", "NUMBER");
        sellerOrderInsightDTO.setData(sellerOrderInsightsList);
        sellerOrderInsightDTO.setLegend("Order Date", "Total Earning");
        sellerOrderInsightDTO.setGridLineFrequency(10, 10);
        return sellerOrderInsightDTO;
    }

    @GetMapping("/api/auth/seller/category/wise/sales/insight")
    public List<InsightDataDTO> getCategoryWiseSales() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<CoordinateDataHolder> customerOrderList = orderRepo.getAllOrdersInsightsSellerCategoryWise(userName);
        int count = 0;
        List<InsightDataDTO> insightDataDTOList = new ArrayList<>();
        for (CoordinateDataHolder cdh : customerOrderList) {
            insightDataDTOList.add(new InsightDataDTO(cdh.getLabel(), count++, cdh.getAggregateValue()));
        }
        return insightDataDTOList;
    }

}
