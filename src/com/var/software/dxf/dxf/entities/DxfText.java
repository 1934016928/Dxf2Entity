package com.var.software.dxf.dxf.entities;

import com.var.software.dxf.dxf.enu.DxfType;

public class DxfText extends DxfEntity {

    private DxfPoint point;
    private double major;
    private String text;
    private float size;
    private String font;

    public DxfText() {
        this.setType(DxfType.TEXT.name());
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }
}
