package com.var.software.dxf.dxf.entities;

import com.var.software.dxf.dxf.enu.DxfType;

/**
 * Create Time: 2018/01/31.
 * Create User: var_rain.
 * Class Usage: Dxf Circle entity object.
 */
public class DxfCircle extends DxfEntity {

    private double x;
    private double y;
    private double r;

    public DxfCircle() {
        this.setType(DxfType.CIRCLE.name());
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

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }
}
