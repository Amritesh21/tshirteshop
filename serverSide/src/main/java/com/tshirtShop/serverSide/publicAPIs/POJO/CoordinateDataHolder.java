package com.tshirtShop.serverSide.publicAPIs.POJO;

public class CoordinateDataHolder {

    String label;
    Long aggregateValue;

    public CoordinateDataHolder(String label, Long aggregateValue) {
        this.label = label;
        this.aggregateValue = aggregateValue;
    }

    public CoordinateDataHolder() {}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getAggregateValue() {
        return aggregateValue;
    }

    public void setAggregateValue(Long aggregateValue) {
        this.aggregateValue = aggregateValue;
    }
}
