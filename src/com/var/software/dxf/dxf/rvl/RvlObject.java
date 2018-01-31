package com.var.software.dxf.dxf.rvl;

import com.var.software.dxf.dxf.DxfLayer;
import com.var.software.dxf.dxf.entities.*;
import com.var.software.dxf.dxf.enu.DxfTags;
import com.var.software.dxf.dxf.enu.DxfType;

import java.util.ArrayList;
import java.util.List;

/**
 * Create Time: 2018/01/31.
 * Create User: var_rain.
 * Class Usage: Resolve entities with dxf object list.
 */
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
        attr.clear();
        dxf.clear();
    }

    /**
     * Split dxf entity.
     *
     * @param dxf dxf part.
     */
    private void rvlSplit(List<DxfAttribute> dxf) {
        DxfAttribute attr = dxf.get(0);
        if (attr.getGroupCode() == 0) {
            if (attr.getGroupValue().equals(DxfType.LINE.name()))
                rvlLine(dxf);
            else if (attr.getGroupValue().equals(DxfType.CIRCLE.name()))
                rvlCircle(dxf);
            else if (attr.getGroupValue().equals(DxfType.LWPOLYLINE.name()))
                rvlLwplyline(dxf);
            else if (attr.getGroupValue().equals(DxfType.ARC.name()))
                rvlArc(dxf);
            else if (attr.getGroupValue().equals(DxfType.ELLIPSE.name()))
                rvlEllipse(dxf);
            else if (attr.getGroupValue().equals(DxfType.HATCH.name()))
                ;// TODO: 18-1-30 Coding later.
            else if (attr.getGroupValue().equals(DxfType.INSERT.name()))
                ;// TODO: 18-1-30 Coding later.
            else if (attr.getGroupValue().equals(DxfType.MTEXT.name()))
                rvlMtext(dxf);
            else if (attr.getGroupValue().equals(DxfType.POINT.name()))
                rvlPoint(dxf);
            else if (attr.getGroupValue().equals(DxfType.SOLID.name()))
                rvlSolid(dxf);
            else if (attr.getGroupValue().equals(DxfType.TEXT.name()))
                rvlText(dxf);
        }
    }

    /**
     * Resolve dxf text objects.
     *
     * @param dxf dxf object list.
     */
    private void rvlText(List<DxfAttribute> dxf) {
        DxfText text = new DxfText();
        DxfPoint point = new DxfPoint();
        for (DxfAttribute attribute : dxf) {
            if (attribute.getGroupCode() == 8) {
                text.setLayerName(attribute.getGroupValue());
            } else if (attribute.getGroupCode() == 10) {
                point.setX(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 20) {
                point.setY(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 40) {
                text.setMajor(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 1) {
                text.setText(attribute.getGroupValue());
            } else if (attribute.getGroupCode() == 41) {
                text.setSize(Float.parseFloat(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 7) {
                text.setFont(attribute.getGroupValue());
            }
        }
        text.setPoint(point);
        layer.addEntity(text);
    }

    /**
     * Resolve dxf solid objects.
     *
     * @param dxf dxf object list.
     */
    private void rvlSolid(List<DxfAttribute> dxf) {
        DxfSolid solid = new DxfSolid();
        DxfPoint a = new DxfPoint();
        DxfPoint b = new DxfPoint();
        DxfPoint c = new DxfPoint();
        DxfPoint d = new DxfPoint();
        for (DxfAttribute attribute : dxf) {
            if (attribute.getGroupCode() == 8) {
                solid.setLayerName(attribute.getGroupValue());
            } else if (attribute.getGroupCode() == 10) {
                a.setX(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 20) {
                a.setY(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 11) {
                b.setX(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 21) {
                b.setY(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 12) {
                c.setX(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 22) {
                c.setY(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 13) {
                d.setX(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 23) {
                d.setY(Double.parseDouble(attribute.getGroupValue()));
            }
        }
        solid.setA(a);
        solid.setB(b);
        solid.setC(c);
        solid.setD(d);
        layer.addEntity(solid);
    }

    /**
     * Resolve dxf mtext objects.
     *
     * @param dxf dxf object list.
     */
    private void rvlMtext(List<DxfAttribute> dxf) {
        DxfMtext mtext = new DxfMtext();
        DxfPoint point = new DxfPoint();
        for (DxfAttribute attribute : dxf) {
            if (attribute.getGroupCode() == 8) {
                mtext.setLayerName(attribute.getGroupValue());
            } else if (attribute.getGroupCode() == 10) {
                point.setX(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 20) {
                point.setY(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 40) {
                mtext.setMajor(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 41) {
                mtext.setAxis(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 1) {
                mtext.setCommand(attribute.getGroupValue());
            }
        }
        mtext.setPoint(point);
        layer.addEntity(mtext);
    }

    /**
     * Resolve dxf ellipse object.
     *
     * @param dxf dxf object list.
     */
    private void rvlEllipse(List<DxfAttribute> dxf) {
        DxfEllipse ellipse = new DxfEllipse();
        DxfPoint center = new DxfPoint();
        DxfPoint first = new DxfPoint();
        for (DxfAttribute attribute : dxf) {
            if (attribute.getGroupCode() == 8) {
                ellipse.setLayerName(attribute.getGroupValue());
            } else if (attribute.getGroupCode() == 10) {
                center.setX(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 20) {
                center.setY(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 11) {
                first.setX(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 21) {
                first.setY(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 40) {
                ellipse.setRatio(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 41) {
                ellipse.setStartAngle(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 42) {
                ellipse.setEndAngle(Double.parseDouble(attribute.getGroupValue()));
            }
        }
        ellipse.setCenter(center);
        ellipse.setFirst(first);
        layer.addEntity(ellipse);
    }

    /**
     * Resolve dxf arc objects.
     *
     * @param dxf dxf object list.
     */
    private void rvlArc(List<DxfAttribute> dxf) {
        DxfArc arc = new DxfArc();
        DxfPoint start = new DxfPoint();
        DxfPoint end = new DxfPoint();
        for (DxfAttribute attribute : dxf) {
            if (attribute.getGroupCode() == 8) {
                arc.setLayerName(attribute.getGroupValue());
            } else if (attribute.getGroupCode() == 10) {
                start.setX(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 20) {
                start.setY(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 40) {
                arc.setR(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 50) {
                end.setX(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 51) {
                end.setY(Double.parseDouble(attribute.getGroupValue()));
            }
        }
        arc.setStart(start);
        arc.setEnd(end);
        layer.addEntity(arc);
    }

    /**
     * Resolve dxf circle object.
     *
     * @param dxf dxf object list.
     */
    private void rvlCircle(List<DxfAttribute> dxf) {
        DxfCircle circle = new DxfCircle();
        for (DxfAttribute attribute : dxf) {
            if (attribute.getGroupCode() == 8) {
                circle.setLayerName(attribute.getGroupValue());
            } else if (attribute.getGroupCode() == 10) {
                circle.setX(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 20) {
                circle.setY(Double.parseDouble(attribute.getGroupValue()));
            } else if (attribute.getGroupCode() == 40) {
                circle.setR(Double.parseDouble(attribute.getGroupValue()));
            }
        }
        layer.addEntity(circle);
    }

    /**
     * Resolve dxf lwpolyline object.
     *
     * @param dxf dxf object list.
     */
    private void rvlLwplyline(List<DxfAttribute> dxf) {
        DxfLwpolyLine line = new DxfLwpolyLine();
        List<DxfPoint> points = new ArrayList<>();
        DxfPoint point = null;
        for (DxfAttribute attribute : dxf) {
            if (attribute.getGroupCode() == 8) {
                line.setLayerName(attribute.getGroupValue());
            } else if (attribute.getGroupCode() == 10) {
                point = new DxfPoint();
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
