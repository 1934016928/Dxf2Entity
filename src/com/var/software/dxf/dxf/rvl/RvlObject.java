package com.var.software.dxf.dxf.rvl;

import com.var.software.dxf.dxf.DxfLayer;
import com.var.software.dxf.dxf.entities.DxfAttribute;
import com.var.software.dxf.dxf.entities.DxfLine;
import com.var.software.dxf.dxf.entities.DxfLwpolyLine;
import com.var.software.dxf.dxf.entities.DxfPoint;
import com.var.software.dxf.dxf.enu.DxfTags;
import com.var.software.dxf.dxf.enu.DxfType;

import java.util.ArrayList;
import java.util.List;

public class RvlObject {

    private DxfLayer layer;

    /**
     * Init objects.
     */
    public RvlObject() {
        layer = new DxfLayer();
    }

    /**
     * Find all entity
     *
     * @param dxf dxf object list.
     */
    public void rvlEntity(List<DxfAttribute> dxf) {
        List<DxfAttribute> attr = new ArrayList<>();
        boolean isRecord = false;
        for (DxfAttribute attribute : dxf) {
            if (attribute.getGroupCode() == 0) {
                if (attr.size() != 0) {
                    rvlSplit(attr);
                    attr.clear();
                    isRecord = false;
                }
                if (attribute.getGroupValue().equals(DxfTags.HEADER.name())
                        || attribute.getGroupValue().equals(DxfTags.BLOCK.name())
                        || attribute.getGroupValue().equals(DxfTags.BLOCKS.name())
                        || attribute.getGroupValue().equals(DxfTags.CLASSES.name())
                        || attribute.getGroupValue().equals(DxfTags.ENDBLK.name())
                        || attribute.getGroupValue().equals(DxfTags.ENDSEC.name())
                        || attribute.getGroupValue().equals(DxfTags.SECTION.name())
                        || attribute.getGroupValue().equals(DxfTags.APPID.name())
                        || attribute.getGroupValue().equals(DxfTags.BLOCK_RECORD.name())
                        || attribute.getGroupValue().equals(DxfTags.DIMSTYLE.name())
                        || attribute.getGroupValue().equals(DxfTags.ENDTAB.name())
                        || attribute.getGroupValue().equals(DxfTags.LAYER.name())
                        || attribute.getGroupValue().equals(DxfTags.LTYPE.name())
                        || attribute.getGroupValue().equals(DxfTags.STYLE.name())
                        || attribute.getGroupValue().equals(DxfTags.TABLE.name())
                        || attribute.getGroupValue().equals(DxfTags.VPORT.name())
                        || attribute.getGroupValue().equals(DxfTags.DICTIONARY.name())
                        || attribute.getGroupValue().equals(DxfTags.EOF.name())) continue;
                isRecord = checkRecord(attribute.getGroupValue());
            }
            if (isRecord) {
                attr.add(attribute);
            }
        }
    }

    /**
     * Split dxf entity.
     *
     * @param dxf dxf part.
     */
    private void rvlSplit(List<DxfAttribute> dxf) {
        DxfAttribute attr = dxf.get(0);
        if (attr.getGroupCode() == 0) {
            if (attr.getGroupValue().equals(DxfType.LINE.name())) rvlLine(dxf);
            else if (attr.getGroupValue().equals(DxfType.CIRCLE.name())) rvlCircle(dxf);
            else if (attr.getGroupValue().equals(DxfType.LWPOLYLINE.name())) rvlLwplyline(dxf);
            else if (attr.getGroupValue().equals(DxfType.ARC.name()))
                ;
            else if (attr.getGroupValue().equals(DxfType.ELLIPSE.name()))
                ;
            else if (attr.getGroupValue().equals(DxfType.HATCH.name()))
                ;
            else if (attr.getGroupValue().equals(DxfType.INSERT.name()))
                ;
            else if (attr.getGroupValue().equals(DxfType.MTEXT.name()))
                ;
            else if (attr.getGroupValue().equals(DxfType.POINT.name())) rvlPoint(dxf);
            else if (attr.getGroupValue().equals(DxfType.SOLID.name()))
                ;
            else if (attr.getGroupValue().equals(DxfType.TEXT.name()))
                ;
        }
    }

    private void rvlCircle(List<DxfAttribute> dxf) {
        System.out.println("---------------------------------");
        for (DxfAttribute attribute : dxf) {
            System.out.println(attribute.getGroupCode() + "\t" + attribute.getGroupValue());
        }
        System.out.println("---------------------------------");
    }

    /**
     * Resolve dxf lwpolyline object.
     *
     * @param dxf dxf object list.
     */
    private void rvlLwplyline(List<DxfAttribute> dxf) {
        DxfLwpolyLine line = new DxfLwpolyLine();
        List<DxfPoint> points = new ArrayList<>();
        DxfPoint point = new DxfPoint();
        for (DxfAttribute attribute : dxf) {
            if (attribute.getGroupCode() == 8) {
                line.setLayerName(attribute.getGroupValue());
            } else if (attribute.getGroupCode() == 10) {
                point.setX(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 20) {
                point.setY(Double.parseDouble(attribute.getGroupValue()));
                points.add(point);
            }
        }
        line.setPoints(points);
        layer.addEntity(line);
    }

    /**
     * Resolve dxf point entity.
     *
     * @param dxf dxf object list.
     */
    private void rvlPoint(List<DxfAttribute> dxf) {
        DxfPoint point = new DxfPoint();
        for (DxfAttribute attribute : dxf) {
            if (attribute.getGroupCode() == 8) {
                point.setLayerName(attribute.getGroupValue());
            } else if (attribute.getGroupCode() == 10) {
                point.setX(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 20) {
                point.setY(Double.parseDouble(attribute.getGroupValue()));
            }
        }
        this.layer.addEntity(point);
    }

    /**
     * Resolve dxf line entity.
     *
     * @param dxf dxf object list.
     */
    private void rvlLine(List<DxfAttribute> dxf) {
        DxfLine line = new DxfLine();
        DxfPoint start = new DxfPoint();
        DxfPoint end = new DxfPoint();
        for (DxfAttribute attribute : dxf) {
            if (attribute.getGroupCode() == 8) {
                line.setLayerName(attribute.getGroupValue());
            } else if (attribute.getGroupCode() == 10) {
                start.setX(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 20) {
                start.setY(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 11) {
                end.setX(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 21) {
                end.setY(Double.parseDouble(attribute.getGroupValue()));
            }
        }
        line.setStart(start);
        line.setEnd(end);
        this.layer.addEntity(line);
    }

    /**
     * Check can record dxf objects.
     *
     * @param value dxf tag.
     * @return If can record, return true, If can't record, return false.
     */
    private boolean checkRecord(String value) {
        return value.equals(DxfType.LINE.name())
                || value.equals(DxfType.CIRCLE.name())
                || value.equals(DxfType.LWPOLYLINE.name())
                || value.equals(DxfType.ARC.name())
                || value.equals(DxfType.ELLIPSE.name())
                || value.equals(DxfType.HATCH.name())
                || value.equals(DxfType.INSERT.name())
                || value.equals(DxfType.MTEXT.name())
                || value.equals(DxfType.POINT.name())
                || value.equals(DxfType.SOLID.name())
                || value.equals(DxfType.TEXT.name());
    }

    /**
     * Get DxfLayer object.
     *
     * @return Return DxfLayer object.
     */
    public DxfLayer getLayer() {
        return layer;
    }

    /**
     * Set DxfLayer object.
     *
     * @param layer DxfLayer object.
     */
    public void setLayer(DxfLayer layer) {
        this.layer = layer;
    }
}
