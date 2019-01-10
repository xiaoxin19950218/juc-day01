package com.atguigu.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author zx
 *һ���̳߳�:�ṩ��һ���̶߳��У������б��������еȴ�״̬���̣߳������˴��������ٶ��⿪���������Ӧ�ٶȡ�
 *�����̳߳ص���ϵ�ṹ
 *		java.util.concurrent.Exector:�����̵߳�ʹ������ȵĸ��ӿ�
 *		 |---ExecutorService �ӽӿ� ���̳߳ص���Ҫ�ӿ�
 *			|---ThreadPoolExecutor �̳߳ص�ʵ����
 *			|---ScheduledExecutorService �ӽӿڣ������̵߳���
 *				|---ScheduledThreadPoolExecutor: 
 *	��.������ Executors
 *	ExecutorService   newFixedThreadPool():�����̶���С���̳߳�
 *	ExecutorService	  newCachedThreadPool():�����̳߳أ��̳߳ص��������̶������Ը��������Զ��ĸ���������
 *	ExecutorService   newSingleThreadExector():���������̳߳أ�ֻ��һ���߳�
 *	ScheduledExecutorService   newScheduledThreadPool():�����̶���С���̳߳ء���ʱ��ʱ��ִ������
 *
 *
 */
public class TestThreadPool {
	public static void main(String[] args) {
		//1.����һ���̳߳�
		ExecutorService pool = Executors.newFixedThreadPool(5);
		pool.submit(new Runnable() {
			
			@Override
			public void run() {
				
			}
		});
	}
}
 