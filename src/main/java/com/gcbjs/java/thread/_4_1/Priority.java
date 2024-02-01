package com.gcbjs.java.thread._4_1;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;

import java.util.List;

/**
 * @ClassName Priority
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/31 17:39
 * @Version 1.0
 **/
public class Priority {

    private static volatile boolean notStart = true;

    private static volatile boolean notEnd = true;

    @SneakyThrows
    public static void main(String[] args) {
        List<Job> jobs = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job, "thread-" + i);
            thread.setPriority(priority);
            thread.start();
        }
        notStart = false;
        Thread.sleep(10000);
        notEnd = false;
        for (Job job : jobs) {
            System.out.println("Job Priority: " + job.priority + ", Job Count: " + job.jobCount);
        }
        //Job Priority: 1, Job Count: 1299557
        //Job Priority: 1, Job Count: 1309768
        //Job Priority: 1, Job Count: 1303135
        //Job Priority: 1, Job Count: 1315255
        //Job Priority: 1, Job Count: 1310712
        //Job Priority: 10, Job Count: 1299225
        //Job Priority: 10, Job Count: 1317944
        //Job Priority: 10, Job Count: 1311302
        //Job Priority: 10, Job Count: 1299332
        //Job Priority: 10, Job Count: 1300805
        //最终结果可以看出,优先级没有生效
    }


    static class Job implements Runnable {

        private int priority;

        private int jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {

            while (notStart) {
                Thread.yield();
            }
            while (notEnd) {
                Thread.yield();
                jobCount++;
            }}
    }
}
