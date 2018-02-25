package com.horizon.common.warn.delayqu;

import org.apache.log4j.Logger;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * [任务调度系统]
 * <br>
 * [后台守护线程不断的执行检测工作]
 * </p>
 * @version 1.0
 */
public class TaskQueueDaemonThread {

    private static final Logger LOG = Logger.getLogger(TaskQueueDaemonThread.class);

    private TaskQueueDaemonThread() {
    }

    private static class LazyHolder {
        private static TaskQueueDaemonThread taskQueueDaemonThread = new TaskQueueDaemonThread();
    }

    public static TaskQueueDaemonThread getInstance() {
        return LazyHolder.taskQueueDaemonThread;
    }

    Executor executor = Executors.newFixedThreadPool(20);
    /**
     * 守护线程
     */
    private Thread daemonThread;

    /**
     * 初始化守护线程
     */
    public void init() {
        daemonThread = new Thread(new Runnable(){

			@Override
			public void run() {
				execute();
			}
        	
        });
        daemonThread.setDaemon(true);
        daemonThread.setName("Task Queue Daemon Thread");
        daemonThread.start();
    }

    private void execute() {
    	LOG.info("start:" + System.currentTimeMillis());
        while (true) {
            try {
                //从延迟队列中取值,如果没有对象过期则队列一直等待，
                Task<?> t1 = t.take();
                if (t1 != null) {
                    //修改问题的状态
                    Runnable task = t1.getTask();
                    if (task == null) {
                        continue;
                    }
                    executor.execute(task);
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * 创建一个最初为空的新 DelayQueue
     */
    private DelayQueue<Task<?>> t = new DelayQueue<Task<?>>();

    /**
     * 添加任务，
     * time 延迟时间
     * task 任务
     * 用户为问题设置延迟时间
     */
    public void put(long time, Runnable task) {
        //转换成ns
        long nanoTime = TimeUnit.NANOSECONDS.convert(time, TimeUnit.MILLISECONDS);
        //创建一个任务
        Task<Runnable> k = new Task<Runnable>(nanoTime, task);
        //将任务放在延迟的队列中
        t.put(k);
    }
    
    /**
     * 队列中的任务数，
     */
    public int size() {
        //转换成ns
       return t.size();
    }

    /**
     * 结束订单
     * @param task
     */
    public boolean endTask(Task<Runnable> task){
    	boolean f = t.remove(task);
        return f;
    }
}