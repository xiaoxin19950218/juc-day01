package com.atguigu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable 的实现
 * 一、创建执行线程的方式三：实现Callable接口。相较于实现Runnable方式。可以有返回值，可以抛出异常
 * 二、执行Callable方式，需要FutureTask实现类的支持，用于接收运算结果，是Future接口的实现类
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
