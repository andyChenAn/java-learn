package thread;

/**
 * �����̺߳͵ȴ��߳�
 * ����Ҳ�����ڵ���wait()������ʱ�򣬴���һ����������wait(long timeout)
 * ���ȴ���ʱ�䳬��ָ����ʱ�䣬��ô��ǰ�̻߳ᱻ���ѣ����������̻߳�øö���ͬ�����������øö����notify()��notifyAll()���������ѵ�ǰ�߳�
 * @author andychen
 *
 */

class WaitAndNotifyThread2 extends Thread {
	public void run () {
		
		synchronized (this) {
			try {
				System.out.println(Thread.currentThread().getName() + "�߳�ִ��");
				Thread.sleep(1000);
				notify();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class WaitAndNotifyThreadTest2 {
	public static void main (String[] args) {
		Thread t1 = new WaitAndNotifyThread2();
		t1.setName("t1");
		synchronized (t1) {
			try {
				System.out.println(Thread.currentThread().getName() + "�߳̿�ʼִ����");
				t1.start();
				
				System.out.println(Thread.currentThread().getName() + "�߳̽���ȴ�״̬");
				t1.wait(3000);
				
				System.out.println(Thread.currentThread().getName() + "�̼߳���ִ��");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
















