package com.atguigu.juc;


import java.util.concurrent.RecursiveTask;

public class ForkJoinWork extends RecursiveTask<Long> {

    private Long start;//��ʼֵ
    private Long end;//����ֵ
    public static final  Long critical = 100000L;//�ٽ�ֵ

    public ForkJoinWork(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        //�ж��Ƿ��ǲ�����
        Long lenth = end - start;
        if(lenth<=critical){
            //��������Ͼ����
            Long sum = 0L;
            for (Long i = start;i<=end;i++){
                sum += i;
            }
            return sum;
        }else {
            //û�в����ϾͿ�ʼ���
            Long middle = (end + start)/2;//���������ֵ���м�ֵ
            ForkJoinWork right = new ForkJoinWork(start,middle);
            right.fork();//��֣���ѹ���̶߳���
            ForkJoinWork left = new ForkJoinWork(middle+1,end);
            left.fork();//��֣���ѹ���̶߳���

            //�ϲ�
            return right.join() + left.join();
        }
    }
}