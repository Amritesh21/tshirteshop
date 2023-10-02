package com.tshirtShop.serverSide.publicAPIs.DTO;

public class InsightDataDTO {

    String xlabel;
    int x;
    Long y;

    public InsightDataDTO(String xlabel, int x, Long y) {
        this.xlabel = xlabel;
        this.x = x;
        this.y = y;
    }

    public InsightDataDTO() {}

    public String getXlabel() {
        return xlabel;
    }

    public void setXlabel(String xlabel) {
        this.xlabel = xlabel;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
