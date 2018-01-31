package com.var.software.dxf.jvm;

/**
 * Create Time: 2018/01/31.
 * Create User: var_rain.
 * Class Usage: Print java jvm memory use info.
 */
public class RuntimeThread extends Thread {

    private boolean isRUn;

    /**
     * Init filed.
     */
    public RuntimeThread() {
        isRUn = true;
    }

    @Override
    public void run() {
        long allMemory;
        long freeMemory;
        Runtime runtime = Runtime.getRuntime();
        while (isRUn) {
            allMemory = (runtime.totalMemory() / 1024) / 1024;
            freeMemory = (runtime.freeMemory() / 1024) / 1024;
            System.out.println("use memory: " + allMemory + "M\t" + "free memory: " + freeMemory + "M\t");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stop thread method.
     *
     * @param state if state is true, thread continue run, if state is false, thread will be stop.
     */
    public void stopRun(boolean state) {
        isRUn = state;
    }
}
