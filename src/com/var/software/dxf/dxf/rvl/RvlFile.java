package com.var.software.dxf.dxf.rvl;

import com.var.software.dxf.dxf.DxfReader;
import com.var.software.dxf.dxf.entities.DxfAttribute;

import java.io.IOException;
import java.util.List;

public class RvlFile {

    private List<DxfAttribute> dxf;

    public RvlFile(String file) {
        DxfReader reader = new DxfReader();
        try {
            dxf = reader.dxf2Obj(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
