package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写一个程序，开启3个线程，这三个线程的ID分别为 A,B,C每个线程将自己的ID在屏幕 上打印10遍，要求输出的结果必须按序显示
 * 
 * @author zx
 *
 */
public class TestABCAlternateBack {
	public static void main(String[] args) {
		AlternateDemo ad = new AlternateDemo();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					ad.loopA();
				}
			}
		}, "A").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					ad.loopB();
				}

			}
		}, "B").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					ad.loopC();
					System.out.println("-----------------------------------");
				}
			}
		}, "C").start();
	}
}

class AlternateDemo {
	private int number = 1;// 当前正在执行线程的标记

	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();

	/**
	 * 
	 * @param totalLoop
	 *            循环几轮
	 */
	public void loopA() {
		lock.lock();
		try {
			// 1.判断
			if (number != 1) {
				condition1.await();
			}
			// 2.打印
			System.out.println(Thread.currentThread().getName());
			// 3.唤醒
			number = 2;
			condition2.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	/**
	 * 
	 * @param totalLoop
	 *            循环几轮
	 */
	public void loopB() {
		lock.lock();
		try {
			// 1.判断
			if (number != 2) {
				condition2.await();
			}
			// 2.打印
			System.out.println(Thread.currentThread().getName());
			// 3.唤醒
			number = 3;
			condition3.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	/**
	 * 
	 * @param totalLoop
	 *            循环几轮
	 */
	public void loopC() {
		lock.lock();
		try {
			// 1.判断
			if (number != 3) {
				condition3.await();
			}
			// 2.打印
			System.out.println(Thread.currentThread().getName());
			// 3.唤醒
			number = 1;
			condition1.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}
