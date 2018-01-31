package com.var.software.dxf.dxf.entities;

import com.var.software.dxf.dxf.enu.DxfType;

/**
 * Create Time: 2018/01/31.
 * Create User: var_rain.
 * Class Usage: Dxf Arc entity object.
 */
public class DxfArc extends DxfEntity {

    private DxfPoint start;
    private DxfPoint end;
    private double r;

    public DxfArc() {
        this.setType(DxfType.ARC.name());
    }

    public DxfPoint getStart() {
        return start;
    }

    public void setStart(DxfPoint start) {
        this.start = start;
    }

    public DxfPoint getEnd() {
        return end;
    }

    public void setEnd(DxfPoint end) {
        this.end = end;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }
}
