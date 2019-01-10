package com.atguigu.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//��д��
/**
 * ReadWriteLock:��д��
 * 
 * дд/��д  ��Ҫ����
 * ����  ����Ҫ�����
 * @author zx
 *
 */
public class TestReadWriteLock {
	public static void main(String[] args) {
		ReadWriteLockDemo rw=new ReadWriteLockDemo();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				rw.set((int)(Math.random()*101));
			}
		},"Write:").start();
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					rw.get();
				}
			}).start();;  
		}
	}
}
class ReadWriteLockDemo{
	private int number=0;
	private ReadWriteLock lock=new ReentrantReadWriteLock();
	
	//��
	public void get(){
		lock.readLock().lock();//����
		try {
			System.out.println(Thread.currentThread().getName()+":"+number);
		} catch (Exception e) {
		} finally{
			lock.readLock().unlock();//�ͷ���
		}
		
	}
	//д
	public void set(int number){
		lock.writeLock().lock();//����
		try {
			System.out.println(Thread.currentThread().getName());
			this.number=number;
		} catch (Exception e) {
		} finally{
			lock.writeLock().unlock();//�ͷ���
		}
		
	}
}