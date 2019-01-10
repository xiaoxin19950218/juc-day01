package com.atguigu.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//¶ÁÐ´Ëø
/**
 * ReadWriteLock:¶ÁÐ´Ëø
 * 
 * Ð´Ð´/¶ÁÐ´  ÐèÒª»¥³â
 * ¶Á¶Á  ²»ÐèÒª»¥³âµÄ
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
	
	//¶Á
	public void get(){
		lock.readLock().lock();//ÉÏËø
		try {
			System.out.println(Thread.currentThread().getName()+":"+number);
		} catch (Exception e) {
		} finally{
			lock.readLock().unlock();//ÊÍ·ÅËø
		}
		
	}
	//Ð´
	public void set(int number){
		lock.writeLock().lock();//ÉÏËø
		try {
			System.out.println(Thread.currentThread().getName());
			this.number=number;
		} catch (Exception e) {
		} finally{
			lock.writeLock().unlock();//ÊÍ·ÅËø
		}
		
	}
}