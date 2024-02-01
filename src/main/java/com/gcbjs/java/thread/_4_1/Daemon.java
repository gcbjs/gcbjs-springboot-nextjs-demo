package com.gcbjs.java.thread._4_1;

/**
 * @ClassName Daemon
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/31 21:55
 * @Version 1.0
 **/
public class Daemon {

    static class DaemonThread implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //虚拟机退出时，守护线程也会退出，同时不会执行finally
                    System.out.println("DaemonThread finally");
                }
                }
            }

    }

    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonThread());
        thread.setDaemon(true);
        thread.start();
    }
}
