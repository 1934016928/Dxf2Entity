package com.var.software.dxf.ui;

import com.var.software.dxf.dxf.DxfLayer;
import com.var.software.dxf.dxf.entities.DxfEntity;
import com.var.software.dxf.dxf.entities.DxfLine;
import com.var.software.dxf.dxf.enu.DxfType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.VolatileImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Create Time: 2018/01/31.
 * Create User: var_rain.
 * Class Usage: Java draw view.
 */
public class MainPanel extends JPanel {

    private DxfLayer layer;
    private List<String> layerNames;
    private MainWindow window;
    private float scale = 1;
    private float moveX = 0;
    private float moveY = 0;
    private boolean isRuler = true;
    private boolean isCkx = false;
    private boolean reLayer = true;
    private int lineX, lineY;
    private VolatileImage image;
    private Graphics2D g2d;

    /**
     * Init object(s).
     */
    public MainPanel() {
        layerNames = new ArrayList<>();
        this.setDoubleBuffered(true);
    }

    /**
     * Set dxf layer object.
     *
     * @param layer DxfLayer.
     */
    public void setLayer(DxfLayer layer, MainWindow window) {
        this.layer = layer;
        this.window = window;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        image = createVolatileImage(width, height);
    }

    /**
     * Get dxf layer object.
     *
     * @return DxfLayer.
     */
    public DxfLayer getLayer() {
        return layer;
    }

    @Override
    public void paint(Graphics g) {
        if (layer != null) {
            if (image == null || image.validate(getGraphicsConfiguration()) == VolatileImage.IMAGE_INCOMPATIBLE) {
                image = createVolatileImage(this.getSize().width, this.getSize().height);
            }
            g2d = image.createGraphics();
            /*Convert left bottom is origin.*/
            g2d.translate(0, getSize().height);
            g2d.scale(1, -1);
            g2d.clearRect(0, 0, image.getWidth(), image.getHeight());
            /*Start draw.*/
            drawDxf(g2d);
            g2d.dispose();
            g.drawImage(image, 0, 0, this);
        }
        g.dispose();
    }

    /**
     * Draw dxf entities.
     *
     * @param g draw object.
     */
    public void drawDxf(Graphics2D g) {
        /*--------MSAA--------*/
        /*g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);*/
        /*--------MSAA--------*/
        draw(g);
        Iterator it = layer.getLayers().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Map<String, List<DxfEntity>>> entry = (Map.Entry<String, Map<String, List<DxfEntity>>>) it.next();
            if (reLayer) {
                layerNames.add(entry.getKey());
            }
            if (entry.getValue().get(DxfType.LINE.name()) != null) {
                for (DxfEntity entity : entry.getValue().get(DxfType.LINE.name())) {
                    drawLine(entity, g);
                }
            }
        }
        reLayer = false;
    }

    /**
     * Draw other object.
     *
     * @param g2d draw object.
     */
    private void draw(Graphics2D g2d) {
        if (isCkx) {
            drawCkx(g2d);
        }
        if (isRuler) {
            drawRuler(g2d);
        }
    }

    /**
     * Draw refer line.
     *
     * @param g2d draw object.
     */
    private void drawCkx(Graphics2D g2d) {
        g2d.setPaint(Color.red);
        g2d.drawLine(lineX, 0, lineX, getSize().height);
        g2d.drawLine(0, getSize().height - lineY, getSize().width, getSize().height - lineY);
        g2d.setPaint(Color.black);
    }

    /**
     * Draw ruler at x and y.
     *
     * @param g2d draw object.
     */
    private void drawRuler(Graphics2D g2d) {

    }

    /**
     * Draw dxf Line(s).
     *
     * @param entity DxfEntity.
     * @param g      draw object.
     */
    private void drawLine(DxfEntity entity, Graphics2D g) {
        DxfLine line = (DxfLine) entity;
        Line2D.Double line2D = new Line2D.Double(
                line.getStart().getX() * scale + moveX,
                line.getStart().getY() * scale + moveY,
                line.getEnd().getX() * scale + moveX,
                line.getEnd().getY() * scale + moveY);
        g.draw(line2D);
    }

    /**
     * Get all layer name.
     *
     * @return return List<String> layer name(s).
     */
    public List<String> getLayerNames() {
        return layerNames;
    }

    /**
     * Set layer name.
     *
     * @param layerNames layer name.
     */
    public void setLayerNames(List<String> layerNames) {
        this.layerNames = layerNames;
    }

    /**
     * Get view scale size.
     *
     * @return return scale size.
     */
    public float getScale() {
        return scale;
    }

    /**
     * Set view scale size.
     *
     * @param scale size value.
     */
    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getMoveX() {
        return moveX;
    }

    public void setMoveX(float moveX) {
        this.moveX = moveX;
    }

    public float getMoveY() {
        return moveY;
    }

    public void setMoveY(float moveY) {
        this.moveY = moveY;
    }

    public void setRuler(boolean ruler) {
        isRuler = ruler;
    }

    public void setCkx(boolean ckx) {
        isCkx = ckx;
    }

    public void setLineX(int lineX) {
        this.lineX = lineX;
    }

    public void setLineY(int lineY) {
        this.lineY = lineY;
    }
}
