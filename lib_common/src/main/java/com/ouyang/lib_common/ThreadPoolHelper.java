package com.ouyang.lib_common;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : ryan
 * 线程池帮助类
 */
public class ThreadPoolHelper {
    public static final String TAG = "ThreadPoolHelper";
    /**
     * CPU 核数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /**
     * 线程池核心线程数
     */
    private static final int CORE_POOL_SIZE = Math.max(3, Math.min(CPU_COUNT - 1, 5));
    /**
     * 线程池线程数的最大值
     */
    private static final int MAXIMUM_POOL_SIZE = CORE_POOL_SIZE;

    /**
     * 线程空置回收时间
     */
    private static final int KEEP_ALIVE_SECONDS = 5;

    private ThreadPoolExecutor threadPoolExecutor;

    public static ThreadPoolHelper getInstance() {
        return ThreadPoolHolder.INSTANCE;
    }

    private static class ThreadPoolHolder {
        private static final ThreadPoolHelper INSTANCE = new ThreadPoolHelper();
    }

    private ThreadPoolHelper() {
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(), new CustomThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }

    public void execute(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

//    public void shutdownNow() {
//        threadPoolExecutor.shutdownNow();
//    }
//
//    public void shutdown() {
//        threadPoolExecutor.shutdown();
//    }

    class CustomThreadFactory implements ThreadFactory {
        private int threadInitNumber;

        private synchronized int nextThreadNum() {
            return threadInitNumber++;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(TAG + "-" + nextThreadNum());
            return thread;
        }
    }


}
