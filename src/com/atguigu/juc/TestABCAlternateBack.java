package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ��дһ�����򣬿���3���̣߳��������̵߳�ID�ֱ�Ϊ A,B,Cÿ���߳̽��Լ���ID����Ļ �ϴ�ӡ10�飬Ҫ������Ľ�����밴����ʾ
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
	private int number = 1;// ��ǰ����ִ���̵߳ı��

	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();

	/**
	 * 
	 * @param totalLoop
	 *            ѭ������
	 */
	public void loopA() {
		lock.lock();
		try {
			// 1.�ж�
			if (number != 1) {
				condition1.await();
			}
			// 2.��ӡ
			System.out.println(Thread.currentThread().getName());
			// 3.����
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
	 *            ѭ������
	 */
	public void loopB() {
		lock.lock();
		try {
			// 1.�ж�
			if (number != 2) {
				condition2.await();
			}
			// 2.��ӡ
			System.out.println(Thread.currentThread().getName());
			// 3.����
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
	 *            ѭ������
	 */
	public void loopC() {
		lock.lock();
		try {
			// 1.�ж�
			if (number != 3) {
				condition3.await();
			}
			// 2.��ӡ
			System.out.println(Thread.currentThread().getName());
			// 3.����
			number = 1;
			condition1.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}