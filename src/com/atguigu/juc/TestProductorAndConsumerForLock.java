package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者和消费者案例
 * 
 * @author zx
 *
 */
public class TestProductorAndConsumerForLock {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		Productor productor = new Productor(clerk);
		Consuner consuner = new Consuner(clerk);
		new Thread(productor, "生产者A").start();
		new Thread(consuner, "消费者B").start();
		new Thread(productor, "生产者C").start();
		new Thread(consuner, "消费者D").start();
	}
}

// 店员
class Clerk2 {
	private int product = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition=lock.newCondition();
	// 进货
	public void get() {
		lock.lock();
		try {
			while (product >= 1) {// 为了避免虚拟唤醒，应该总是使用在循环中。
				System.out.println("产品已满！");
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

	// 卖货
	public void sale() {// product=1 循环次数 ：0
		lock.lock();
		try {
			while (product <= 0) {
				System.out.println("缺货！");
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

// 生产者
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

// 消费者
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
