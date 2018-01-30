package com.var.software.dxf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.var.software.dxf.dxf.rvl.RvlFile;
import com.var.software.dxf.dxf.rvl.RvlObject;
import com.var.software.dxf.jvm.RuntimeThread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        RuntimeThread thread = new RuntimeThread();
        thread.start();
        PrintWriter writer = new PrintWriter(new FileWriter(new File("/home/var_rain/files/dxf.json")));
        long time = System.currentTimeMillis();
        RvlFile rvl = new RvlFile("/home/var_rain/Java/dxf/assets/test.dxf");
        RvlObject object = new RvlObject();
        object.rvlEntity(rvl.getDxf());
        Gson json = new GsonBuilder().setPrettyPrinting().create();
        writer.write(json.toJson(object.getLayer().getLayers()));
        writer.flush();
        writer.close();
        System.out.println("use time: " + (System.currentTimeMillis() - time) + "ms");
        thread.stopRun(false);
    }
}
