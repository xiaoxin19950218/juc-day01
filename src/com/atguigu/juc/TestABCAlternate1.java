package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * ��дһ�����򣬿���3���̣߳��������̵߳�ID�ֱ�Ϊ A,B,Cÿ���߳̽��Լ���ID����Ļ �ϴ�ӡ10�飬Ҫ������Ľ�����밴����ʾ
 * @author zx
 *
 */
public class TestABCAlternate1 {
	public static void main(String[] args) {
		AlterDemo aDemo=new AlterDemo();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 20; i++) {
					aDemo.loopA(i);
				}
			}
		},"A").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 20; i++) {
					aDemo.loopB(i);
				}
			}
		},"B").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 20; i++) {
					aDemo.loopC(i);
				}
			}
		},"C").start();
	}
}
class AlterDemo{
	private Lock lock;
	Condition condition1=lock.newCondition();
	Condition condition2=lock.newCondition();
	Condition condition3=lock.newCondition();
	private int number=1;
	public void loopA(int total){
		lock.lock();
		try {
			if (number!=1) {
				condition1.await();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+total);
			}
			number=2;
			condition2.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public void loopB(int total){
		lock.lock();
		try {
			if (number!=2) {
				condition2.await();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+total);
			}
			number=3;
			condition3.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public void loopC(int total){
		lock.lock();
		try {
			if (number!=3) {
				condition3.await();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+total);
			}
			number=1;
			condition1.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}