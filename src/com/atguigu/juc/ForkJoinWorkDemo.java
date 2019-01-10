package com.atguigu.juc;

import org.junit.Test; 
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinWorkDemo {
	@Test
    public  void test() {
	//ForkJoinʵ��
        long l = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();//ʵ��ForkJoin �ͱ�����ForkJoinPool��֧��
        ForkJoinTask<Long> task = new ForkJoinWork(0L,10000000000L);//����Ϊ��ʼֵ�����ֵ
        Long invoke = forkJoinPool.invoke(task);
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + invoke+"  time: " + (l1-l));
        //invoke = -5340232216128654848  time: 76474
    }
    @Test
    public void test2(){
    	//��ͨ�߳�ʵ��
        Long x = 0L;
        Long y = 10000000000L;
        long l = System.currentTimeMillis();
        for (Long i = 0L; i <= y; i++) {
            x+=i;
        }
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + x+"  time: " + (l1-l));
        //invoke = -5340232216128654848  time: 160939
    }
    @Test
    public void test3(){
    	//Java 8 ��������ʵ��
        long l = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0, 10000000000L).parallel().reduce(0, Long::sum);
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + reduce+"  time: " + (l1-l));
        //invoke = -5340232216128654848  time: 15531
    }

}