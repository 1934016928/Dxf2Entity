package com.var.software.dxf.jvm;

public class RuntimeThread extends Thread {

    private boolean isRUn;

    public RuntimeThread() {
        isRUn = true;
    }

    @Override
    public void run() {
        long allMemory;
        long freeMemory;
        long maxMemory;
        Runtime runtime = Runtime.getRuntime();
        while (isRUn) {
            allMemory = (runtime.totalMemory() / 1024) / 1024;
            freeMemory = (runtime.freeMemory() / 1024) / 1024;
            maxMemory = (runtime.maxMemory() / 1024) / 1024;
            System.out.println("use memory: " + allMemory + "M\t" + "free memory: " + freeMemory + "M\t" + "max memory: " + maxMemory + "M\t" + "process: " + runtime.availableProcessors());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopRun(boolean state) {
        isRUn = state;
    }
}
