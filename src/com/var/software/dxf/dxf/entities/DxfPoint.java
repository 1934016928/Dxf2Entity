package com.var.software.dxf.dxf.entities;

import com.var.software.dxf.dxf.enu.DxfType;

/**
 * Create Time: 2018/01/31.
 * Create User: var_rain.
 * Class Usage: Dxf Point object.
 */
public class DxfPoint extends DxfEntity {

    private double x;
    private double y;

    public DxfPoint() {
        this.setType(DxfType.POINT.name());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
