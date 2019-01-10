package com.atguigu.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author zx
 *一、线程池:提供了一个线程队列，队列中保存着所有等待状态的线程，避免了创建和销毁额外开销，提高响应速度。
 *二、线程池的体系结构
 *		java.util.concurrent.Exector:负责线程的使用与调度的根接口
 *		 |---ExecutorService 子接口 ：线程池的主要接口
 *			|---ThreadPoolExecutor 线程池的实现类
 *			|---ScheduledExecutorService 子接口：负责线程调度
 *				|---ScheduledThreadPoolExecutor: 
 *	三.工具类 Executors
 *	ExecutorService   newFixedThreadPool():创建固定大小的线程池
 *	ExecutorService	  newCachedThreadPool():缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
 *	ExecutorService   newSingleThreadExector():创建单个线程池，只有一个线程
 *	ScheduledExecutorService   newScheduledThreadPool():创建固定大小的线程池。延时或定时的执行任务
 *
 *
 */
public class TestThreadPool {
	public static void main(String[] args) {
		//1.创建一个线程池
		ExecutorService pool = Executors.newFixedThreadPool(5);
		pool.submit(new Runnable() {
			
			@Override
			public void run() {
				
			}
		});
	}
}
 