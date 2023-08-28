package com.tshirtShop.serverSide.publicAPIs.DTO;

import com.tshirtShop.serverSide.publicAPIs.POJO.SellerOrderInsights;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class DataType {
    String xAxis;
    String yAxis;

    public DataType(String xAxis, String yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public String getxAxis() {
        return xAxis;
    }

    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    public String getyAxis() {
        return yAxis;
    }

    public void setyAxis(String yAxis) {
        this.yAxis = yAxis;
    }
}

class Legend {
    String x;
    String y;

    public Legend(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}

class GridLineFrequency {
    int x;
    int y;

    public GridLineFrequency(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

class Data {
    String x;
    Long y;

    public Data(String x, Long y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}

public class SellerOrderInsightDTO {

    DataType dataType;
    List<Data> data;
    Legend legend;
    GridLineFrequency gridLineFrequency;

    public void setDataType(String xAxis, String yAxis) {
        dataType = new DataType(xAxis, yAxis);
    }

    public DataType getDataType() {
        return dataType;
    }

    public  void setData(List<SellerOrderInsights> sellerOrderInsightsList) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        HashMap<String, Long> sellerOrderInsightMap = new HashMap<>();
        for (SellerOrderInsights sellerOrderInsight : sellerOrderInsightsList) {
            String orderDate = format.format(sellerOrderInsight.getOrderDate());
            if (sellerOrderInsightMap.containsKey(orderDate)) {
                var totalCost = sellerOrderInsight.getTotalCost() + sellerOrderInsightMap.get(orderDate);
                sellerOrderInsightMap.put(orderDate, totalCost);
            } else {
                sellerOrderInsightMap.put(orderDate, sellerOrderInsight.getTotalCost());
            }
        }

        data = new ArrayList<>();
        for( String orderDate : sellerOrderInsightMap.keySet()) {
            Data dataPoint = new Data(orderDate, sellerOrderInsightMap.get(orderDate));
            data.add(dataPoint);
        }
        data = data.stream().sorted((x, y) -> {
            try {
                return format.parse(x.getX()).compareTo(format.parse(y.getX()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public List<Data> getData() {
        return data;
    }

    public void setLegend(String x, String y) {
        legend = new Legend(x, y);
    }

    public Legend getLegend() {
        return legend;
    }

    public void setGridLineFrequency(int x , int y) {
        gridLineFrequency = new GridLineFrequency(x ,y);
    }

    public GridLineFrequency getGridLineFrequency() {
        return gridLineFrequency;
    }


}
