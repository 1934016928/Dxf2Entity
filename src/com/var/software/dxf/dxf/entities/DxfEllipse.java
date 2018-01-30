package com.var.software.dxf.dxf.entities;

import com.var.software.dxf.dxf.enu.DxfType;

public class DxfEllipse extends DxfEntity {

    private DxfPoint center;
    private DxfPoint first;
    private double startAngle;
    private double endAngle;
    private double ratio;

    public DxfEllipse() {
        this.setType(DxfType.ELLIPSE.name());
    }

    public DxfPoint getCenter() {
        return center;
    }

    public void setCenter(DxfPoint center) {
        this.center = center;
    }

    public DxfPoint getFirst() {
        return first;
    }

    public void setFirst(DxfPoint first) {
        this.first = first;
    }

    public double getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
    }

    public double getEndAngle() {
        return endAngle;
    }

    public void setEndAngle(double endAngle) {
        this.endAngle = endAngle;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
