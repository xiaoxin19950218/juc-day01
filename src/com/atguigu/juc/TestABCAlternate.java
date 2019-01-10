package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 编写一个程序，开启 3 个线程，这三个线程的 ID 分别为 A、B、C，每个线程将自己的 ID 在屏幕上打印 10 遍，要求输出的结果必须按顺序显示。
 *	如：ABCABCABC…… 依次递归
 */
public class TestABCAlternate {
	public static void main(String[] args) {
		AlterDe alterDe = new AlterDe();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					alterDe.loopA();
				}
			}
		}, "A").start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					alterDe.loopB();
				}
			}
		}, "B").start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					alterDe.loopC();
				}

			}
		}, "C").start();
	}
}

class AlterDe {
	private Lock lock = new ReentrantLock();;
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();
	private int number = 1;

	public void loopA() {
		lock.lock();
		try {
			if (number != 1) {
				try {
					condition1.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("-----A-----");
			number = 2;
			condition2.signal();
		} finally {
			lock.unlock();
		}
	}

	public void loopB() {
		lock.lock();
		try {
			if (number != 2) {
				try {
					condition2.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("-----B-----");
			number = 3;
			condition3.signal();
		} finally {
			lock.unlock();
		}
	}

	public void loopC() {
		lock.lock();
		try {
			if (number != 3) {
				try {
					condition3.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("-----C-----");
			number = 1;
			condition1.signal();
		} finally {
			lock.unlock();
		}
	}
}