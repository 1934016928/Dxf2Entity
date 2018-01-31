package com.var.software.dxf.ui;

import com.var.software.dxf.dxf.rvl.RvlFile;
import com.var.software.dxf.threads.RefreshThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FilenameFilter;

public class MainWindow extends JFrame implements FilenameFilter, MouseListener {

    private JPanel contentPanel;
    private JComboBox layers;
    private JTextField savePath;
    private JButton openFIle;
    private JButton browser;
    private JButton exportJson;
    private JLabel state;
    private MainPanel mainPanel;
    private JLabel scaleNum;
    private JButton resetView;
    private JTextField scaleValue;
    private JCheckBox openRuler;
    private JCheckBox openCkx;
    private boolean isPressed = false;
    private float evevtX, eventY;

    public MainWindow() {
        this.setTitle("DXF Preview - v1.0.0");
        this.setContentPane(contentPanel);
        this.setMinimumSize(new Dimension(1050, 480));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new RefreshThread(this.mainPanel).start();
        scaleNum.setText("1倍");
        initAction();
    }

    private void initAction() {
        openFIle.addActionListener(e -> {
            FileDialog dialog = new FileDialog(this, "Open File", FileDialog.LOAD);
            dialog.setFilenameFilter(this);
            dialog.setVisible(true);
            if (dialog.getFile() != null || !dialog.getFile().equals("")) {
                String path = dialog.getDirectory() + dialog.getFile();
                state.setText("正在解析文件");
                RvlFile file = new RvlFile(path);
                mainPanel.setLayer(file.start(), MainWindow.this);
                state.setText("运行中");
                MainWindow.this.setTitle(dialog.getFile() + " [" + path + "] - DXF Preview - v1.0.0");
            }
        });
        openRuler.addActionListener(e -> {
            mainPanel.setRuler(openRuler.isSelected());
        });
        openCkx.addActionListener(e -> {
            mainPanel.setCkx(openCkx.isSelected());
        });
        scaleValue.addCaretListener(e -> {
            if (scaleValue.getText() != null && !scaleValue.getText().equals("")) {
                mainPanel.setScale(Float.parseFloat(scaleValue.getText()));
                scaleNum.setText(mainPanel.getScale() + "倍");
            }
        });
        resetView.addActionListener(e -> {
            mainPanel.setScale(1);
            mainPanel.setMoveX(0);
            mainPanel.setMoveY(0);
            scaleNum.setText("1倍");
        });
        mainPanel.addMouseWheelListener(e -> {
            if (e.getWheelRotation() == 1) {
                mainPanel.setScale(mainPanel.getScale() / 1.1f);
            } else {
                mainPanel.setScale(mainPanel.getScale() * 1.1f);
            }
            scaleNum.setText(mainPanel.getScale() + "倍");
        });
        mainPanel.addMouseListener(this);
        mainPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isPressed) {
                    int x = e.getX();
                    int y = e.getY();
                    mainPanel.setLineX(x);
                    mainPanel.setLineY(y);
                    mainPanel.setMoveX(mainPanel.getMoveX() + (x - evevtX));
                    mainPanel.setMoveY(mainPanel.getMoveY() - (y - eventY));
                    evevtX = x;
                    eventY = y;
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                mainPanel.setLineX(x);
                mainPanel.setLineY(y);
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            isPressed = true;
            evevtX = e.getX();
            eventY = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isPressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith("dwg") || name.endsWith("dxf");
    }
}
