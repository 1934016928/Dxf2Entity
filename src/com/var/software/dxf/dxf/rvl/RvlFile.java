package com.var.software.dxf.dxf.rvl;

import com.var.software.dxf.dxf.DxfLayer;
import com.var.software.dxf.dxf.DxfReader;
import com.var.software.dxf.dxf.entities.DxfAttribute;

import java.io.IOException;
import java.util.List;

/**
 * Create Time: 2018/01/31.
 * Create User: var_rain.
 * Class Usage: Read dxf file attribute to dxf object list..
 */
public class RvlFile {

    private List<DxfAttribute> dxf;

    /**
     * Start read dxf file.
     *
     * @param file dxf file path.
     */
    public RvlFile(String file) {
        DxfReader reader = new DxfReader();
        try {
            dxf = reader.dxf2Obj(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start resolve dxf objects.
     *
     * @return return DxfLayer object.
     */
    public DxfLayer start() {
        RvlObject object = new RvlObject();
        object.rvlEntity(dxf);
        return object.getLayer();
    }

    /**
     * Get dxf object list.
     *
     * @return return dxf object list.
     */
    public List<DxfAttribute> getDxf() {
        return dxf;
    }

    /**
     * Set dxf object list.
     *
     * @param dxf dxf object list.
     */
    public void setDxf(List<DxfAttribute> dxf) {
        this.dxf = dxf;
    }
}
