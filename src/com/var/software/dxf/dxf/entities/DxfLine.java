package com.var.software.dxf.dxf.entities;

import com.var.software.dxf.dxf.enu.DxfType;

/**
 * Create Time: 2018/01/31.
 * Create User: var_rain.
 * Class Usage: Dxf Line object.
 */
public class DxfLine extends DxfEntity {

    private DxfPoint start;
    private DxfPoint end;

    public DxfLine() {
        this.setType(DxfType.LINE.name());
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
}
