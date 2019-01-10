package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestProductorAndConsumer1 {
	public static void main(String[] args) {
		Clerk1 clerk = new Clerk1();
		Product productor = new Product(clerk);
		Customer consuner = new Customer(clerk);
		new Thread(productor, "������A").start();
		new Thread(consuner, "������B").start();
		new Thread(productor, "������C").start();
		new Thread(consuner, "������D").start();
	}
}

class Clerk1 {
	private Lock lock=new ReentrantLock();
	private Condition condition=lock.newCondition();
	private int product = 0;
	public void stock(){
		lock.lock();
		try {
			while(product>=1){
				System.out.println("��Ʒ����");
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName()+ ++product);
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	public void sellProds(){
		lock.lock();
		try {
			while(product<=0){
				System.out.println("ȱ��");
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName()+ --product);
		} finally {
			lock.unlock();
		}
	}
}
//������
class Product implements Runnable{
	private Clerk1 clerk;
	public Product(Clerk1 clerk) {
		this.clerk = clerk;
	}
	@Override
	public void run() {
		clerk.stock();
	}
}
//������
class Customer implements Runnable{
	private Clerk1 clerk;
	public Customer(Clerk1 clerk) {
		this.clerk = clerk;
	}
	@Override
	public void run() {
		clerk.sellProds();
	}
}