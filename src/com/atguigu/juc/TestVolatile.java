package com.atguigu.juc;

/**
 * һ��volatile �ؼ���:������߳̽��в�������ʱ�����Ա�֤�ڴ��е����ݿɼ�
 * 		 ��synchronized ��һ�ֽ�Ϊ��������ͬ������
 * 	ע�⣺
 * 		1.volatile ���߱�������
 * 		2.volatile ���ܱ�֤������ԭ����
 * @author zx
 *
 */
public class TestVolatile  {
	public static void main(String[] args) {
		ThreadDenmo td=new  ThreadDenmo();
		new Thread(td).start();
		
		while(true){
			if (td.isFlag()) {
				System.out.println("---------");
				break;
			}
		}
	}
}
	
	
class ThreadDenmo implements Runnable{
	
	private volatile boolean flag=false;
	
	@Override
	public void run() {
		try {
			Thread.sleep(200);
		} catch (Exception e) {
		}
		flag=true;
		System.out.println("flag="+isFlag());
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	

}