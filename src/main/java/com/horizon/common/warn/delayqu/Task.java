package com.horizon.common.warn.delayqu;

/**
 * Created by Administrator on 2017/5/8.
 */
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * [任务调度系统]
 * <br>
 * [队列中要执行的任务]
 * </p>
 *
 * @version 1.0
 * @Date 2015年11月22日19:46:39
 */
public class Task<T extends Runnable> implements Delayed {
    /**
     * 到期时间
     */
    private final long time;

    /**
     * 问题对象
     */
    private final T task;
    private static final AtomicLong atomic = new AtomicLong(0);

    private final long n;

    public Task(long timeout, T t) {
        this.time = System.nanoTime() + timeout;
        this.task = t;
        this.n = atomic.getAndIncrement();
    }

    /**
     * 返回与此对象相关的剩余延迟时间，以给定的时间单位表示
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.time - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        // TODO Auto-generated method stub
        if (other == this) // compare zero ONLY if same object
            return 0;
        if (other instanceof Task) {
            Task x = (Task) other;
            long diff = time - x.time;
            if (diff < 0)
                return -1;
            else if (diff > 0)
                return 1;
            else if (n < x.n)
                return -1;
            else
                return 1;
        }
        long d = (getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }

    public T getTask() {
        return this.task;
    }

    @Override
    public int hashCode() {
        return task.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Task) {
            return object.hashCode() == hashCode() ? true : false;
        }
        return false;
    }


}