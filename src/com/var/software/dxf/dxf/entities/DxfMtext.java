package com.var.software.dxf.dxf.entities;

import com.var.software.dxf.dxf.enu.DxfType;

public class DxfMtext extends DxfEntity {

    private DxfPoint point;
    private double major;
    private double axis;
    private String command;

    public DxfMtext() {
        this.setType(DxfType.MTEXT.name());
    }

    public DxfPoint getPoint() {
        return point;
    }

    public void setPoint(DxfPoint point) {
        this.point = point;
    }

    public double getMajor() {
        return major;
    }

    public void setMajor(double major) {
        this.major = major;
    }

    public double getAxis() {
        return axis;
    }

    public void setAxis(double axis) {
        this.axis = axis;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
