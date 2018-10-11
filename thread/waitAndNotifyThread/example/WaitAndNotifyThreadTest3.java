package thread;

/**
 * �ȴ��̺߳ͻ����߳�
 * @author andychen
 *
 */

public class WaitAndNotifyThreadTest3 {
	
	private static Object obj = new Object();
	
	public static void main (String[] args) {
		
		Thread t1 = new WaitAndNotifyThread3();
		Thread t2 = new WaitAndNotifyThread3();
		Thread t3 = new WaitAndNotifyThread3();
		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");
		System.out.println(Thread.currentThread().getName() + "�߳̿�ʼִ����");
		
		System.out.println(t1.getName() + "�߳̿�ʼִ����");
		t1.start();
		System.out.println(t2.getName() + "�߳̿�ʼִ����");
		t2.start();
		System.out.println(t3.getName() + "�߳̿�ʼִ����");
		t3.start();
		try {
			System.out.println(Thread.currentThread().getName() + "�߳�����3��");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (obj) {
			System.out.println(Thread.currentThread().getName() + "�̵߳���notifyAll����");
			obj.notifyAll();
		}
	}
	
	static class WaitAndNotifyThread3 extends Thread {
		public void run () {
			synchronized (obj) {
				try {
					System.out.println(Thread.currentThread().getName() + "�߳�wait");
					obj.wait();
					System.out.println(Thread.currentThread().getName() + "�߳� continue");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
