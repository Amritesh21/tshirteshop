package com.tshirtShop.serverSide.publicAPIs.DTO;

import java.util.ArrayList;
import java.util.List;

class DataPlot {

    private int x;
    private Long y;
    private String xLabel;

    protected void setX(int x) {
        this.x = x;
    }

    protected void setY(Long y) {
        this.y = y;
    }

    protected void setXLabel(String xLabel) {
        this.xLabel = xLabel;
    }

    protected int getX() {
        return x;
    }

    protected Long getY() {
        return y;
    }

    protected String getXLabel() {
        return xLabel;
    }

}

public class GraphDataDTO {

    List<DataPlot> data = new ArrayList<>();

    public void setList(DataPlot dataPlot) {
        this.data.add(dataPlot);
    }

    public void setDataInList(String xLabel, Long y, int x) {
        var dataPlot = new DataPlot();
        dataPlot.setX(x);
        dataPlot.setY(y);
        dataPlot.setXLabel(xLabel);
        data.add(new DataPlot());
    }

    public List<DataPlot> getData() {
        return data;
    }
}
