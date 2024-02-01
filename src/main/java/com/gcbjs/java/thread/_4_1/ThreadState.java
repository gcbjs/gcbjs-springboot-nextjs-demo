package com.gcbjs.java.thread._4_1;

/**
 * @ClassName ThreadState
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/31 18:02
 * @Version 1.0
 **/
public class ThreadState {

    public static void main(String[] args) {
        new Thread(new Waiting(), "WaitingThread").start();
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        //一个获取成功
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
    }

    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try{
                        Waiting.class.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static class Blocked implements Runnable {
        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
