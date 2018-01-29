package com.var.software.dxf.dxf.rvl;

import com.var.software.dxf.dxf.DxfLayer;
import com.var.software.dxf.dxf.entities.DxfAttribute;

import java.util.ArrayList;
import java.util.List;

public class RvlObject {

    private DxfLayer layer;

    public RvlObject() {
        layer = new DxfLayer();
    }

    public void rvlEntity(List<DxfAttribute> dxf) {
        List<DxfAttribute> attr = new ArrayList<>();
        boolean isRecord = false;
        for (DxfAttribute attribute : dxf) {
            if (attribute.getGroupCode() == 0) {
//                if (attribute.getGroupValue())
            }
        }
    }
}
