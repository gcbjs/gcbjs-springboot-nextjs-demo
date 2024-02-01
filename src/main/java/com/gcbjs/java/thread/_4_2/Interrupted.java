package com.gcbjs.java.thread._4_2;

/**
 * @ClassName Interrupted
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/2/1 07:52
 * @Version 1.0
 **/
public class Interrupted {


    static class BusyThread implements Runnable{
        @Override
        public void run() {
            while (true){

            }
        }
    }


    static class SleepThread implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread sleepThread = new Thread(new SleepThread(),"SleepThread");
        sleepThread.setDaemon(true);
        Thread busyThread = new Thread(new BusyThread(),"BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("sleepThread interrupted: " + sleepThread.isInterrupted());
        System.out.println("busyThread interrupted: " + busyThread.isInterrupted());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
