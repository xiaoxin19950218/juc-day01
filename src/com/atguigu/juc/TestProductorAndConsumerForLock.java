package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �����ߺ������߰���
 * 
 * @author zx
 *
 */
public class TestProductorAndConsumerForLock {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		Productor productor = new Productor(clerk);
		Consuner consuner = new Consuner(clerk);
		new Thread(productor, "������A").start();
		new Thread(consuner, "������B").start();
		new Thread(productor, "������C").start();
		new Thread(consuner, "������D").start();
	}
}

// ��Ա
class Clerk2 {
	private int product = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition=lock.newCondition();
	// ����
	public void get() {
		lock.lock();
		try {
			while (product >= 1) {// Ϊ�˱������⻽�ѣ�Ӧ������ʹ����ѭ���С�
				System.out.println("��Ʒ������");
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + ":" + ++product);
			condition.signalAll();
		} finally {
			lock.unlock();
		}

	}

	// ����
	public void sale() {// product=1 ѭ������ ��0
		lock.lock();
		try {
			while (product <= 0) {
				System.out.println("ȱ����");
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + ":" + --product);
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
}

// ������
class Productor2 implements Runnable {
	private Clerk clerk;

	public Productor2(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			clerk.get();
		}
	}
}

// ������
class Consuner2 implements Runnable {
	private Clerk clerk;

	public Consuner2(Clerk clerk) {
		this.clerk = clerk;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			clerk.sale();
		}
	}
}