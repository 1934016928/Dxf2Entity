package com.var.software.dxf.dxf.entities;

import com.var.software.dxf.dxf.enu.DxfType;

public class DxfSolid extends DxfEntity {

    private DxfPoint a;
    private DxfPoint b;
    private DxfPoint c;
    private DxfPoint d;

    public DxfSolid() {
        this.setType(DxfType.SOLID.name());
    }

    public DxfPoint getA() {
        return a;
    }

    public void setA(DxfPoint a) {
        this.a = a;
    }

    public DxfPoint getB() {
        return b;
    }

    public void setB(DxfPoint b) {
        this.b = b;
    }

    public DxfPoint getC() {
        return c;
    }

    public void setC(DxfPoint c) {
        this.c = c;
    }

    public DxfPoint getD() {
        return d;
    }

    public void setD(DxfPoint d) {
        this.d = d;
    }
}
