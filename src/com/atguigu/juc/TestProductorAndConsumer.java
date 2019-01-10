package com.atguigu.juc;

/**
 * �����ߺ������߰���
 * @author zx
 *
 */
public class TestProductorAndConsumer {
		public static void main(String[] args) {
			Clerk clerk=new Clerk();
			Productor productor=new Productor(clerk);
			Consuner consuner=new Consuner(clerk);
			new  Thread(productor,"������A").start();
			new  Thread(consuner,"������B").start();
			new  Thread(productor,"������C").start();
			new  Thread(consuner,"������D").start();
		}
}
//��Ա
class Clerk{
	private int product=0;
	//����
	public synchronized void get(){
		while (product>=1) {//Ϊ�˱������⻽�ѣ�Ӧ������ʹ����ѭ���С�
			System.out.println("��Ʒ������");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			System.out.println(Thread.currentThread().getName()+":"+ ++product);
			this.notifyAll();
		
	}
	//����
	public synchronized void sale(){// product=1   ѭ������ ��0
		while (product<=0) {
			System.out.println("ȱ����");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+":"+ --product);
		this.notifyAll();
	}
}
//������
class Productor implements Runnable{
	private Clerk clerk;
	public Productor(Clerk clerk) {
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
//������
class Consuner implements Runnable{
	private Clerk clerk;
	public Consuner(Clerk clerk) {
		this.clerk = clerk;
	}
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			clerk.sale();
		}
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	