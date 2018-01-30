package com.var.software.dxf.dxf.entities;

import com.var.software.dxf.dxf.enu.DxfType;

import java.util.List;

public class DxfLwpolyLine extends DxfEntity {

    private List<DxfPoint> points;

    public DxfLwpolyLine() {
        this.setType(DxfType.LWPOLYLINE.name());
    }

    public List<DxfPoint> getPoints() {
        return points;
    }

    public void setPoints(List<DxfPoint> points) {
        this.points = points;
    }
}
