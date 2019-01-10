package com.atguigu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable ��ʵ��
 * һ������ִ���̵߳ķ�ʽ����ʵ��Callable�ӿڡ������ʵ��Runnable��ʽ�������з���ֵ�������׳��쳣
 * ����ִ��Callable��ʽ����ҪFutureTaskʵ�����֧�֣����ڽ�������������Future�ӿڵ�ʵ����
 * @author zx
 *
 */
public class TestCallable {
	public static void main(String[] args) {
		ThreadDemo td=new  ThreadDemo();
		FutureTask<Integer> result = new FutureTask<Integer>(td);
		new Thread(result).start();
		System.out.println("---------------");
		try {
			Integer sum=result.get();
			System.out.println(sum);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}
	
}
class ThreadDemo implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		int sum=0;
		for (int i = 0; i < 100; i++) {
			sum+=i;
		}
		return sum;
	}
	
}