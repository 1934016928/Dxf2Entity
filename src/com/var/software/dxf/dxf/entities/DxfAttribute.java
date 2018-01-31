package com.var.software.dxf.dxf.entities;

/**
 * Create Time: 2018/01/31.
 * Create User: var_rain.
 * Class Usage: Dxf GroupCode & GroupValue entity object.
 */
public class DxfAttribute {

    private int groupCode;
    private String groupValue;

    public DxfAttribute() {

    }

    public DxfAttribute(int groupCode, String groupValue) {
        this.groupCode = groupCode;
        this.groupValue = groupValue;
    }

    public int getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(int groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupValue() {
        return groupValue;
    }

    public void setGroupValue(String groupValue) {
        this.groupValue = groupValue;
    }
}
