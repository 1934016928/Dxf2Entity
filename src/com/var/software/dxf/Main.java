package com.var.software.dxf;

import com.var.software.dxf.jvm.RuntimeThread;
import com.var.software.dxf.ui.MainWindow;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        RuntimeThread thread = new RuntimeThread();
        thread.start();
        MainWindow window = new MainWindow();
        window.pack();
        window.setVisible(true);
        window.addNotify();
//        PrintWriter writer = new PrintWriter(new FileWriter(new File("/home/var_rain/files/dxf.json")));
//        long time = System.currentTimeMillis();
//        RvlFile rvl = new RvlFile("/home/var_rain/Java/dxf/assets/news.dxf");
//        RvlFile rvl = new RvlFile("/home/var_rain/Java/dxf/assets/test.dxf");
//        RvlFile rvl = new RvlFile("/home/var_rain/下载/drawing2.dxf");
//        DxfLayer layer = rvl.start();
//        Gson json = new Gson();
//        Gson json = new GsonBuilder().setPrettyPrinting().create();
//        writer.write(json.toJson(layer.getLayers()));
//        writer.flush();
//        writer.close();
//        System.out.println("use time: " + (System.currentTimeMillis() - time) + "ms");
//        thread.stopRun(false);
    }
}
