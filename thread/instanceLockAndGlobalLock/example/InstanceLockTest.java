package thread;
/**
 * ʵ����
 * �������̣߳�����ͬһ��ʵ����ʱ��һ���̻߳��ʵ��������һ���̱߳���ȴ���ֱ�����߳��ͷ�ʵ����Ϊֹ
 * @author andychen
 *
 */

class InstanceLcok {
	public void synchronizedMethod1 () {
		synchronized (this) {
			for (int i = 0 ; i < 3 ; i++) {
				try {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + "ѭ����" + i + "��");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void synchronizedMethod2 () {
		synchronized (this) {
			for (int i = 0 ; i < 3 ; i++) {
				try {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + "ѭ����" + i + "��");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

public class InstanceLockTest {
	public static void main (String[] args) {
		InstanceLcok instanceLock = new InstanceLcok();
		Thread t1 = new Thread(new Runnable () {
			public void run () {
				instanceLock.synchronizedMethod1();
			}
		} , "t1");
		Thread t2 = new Thread(new Runnable () {
			public void run () {
				instanceLock.synchronizedMethod2();
			}
		} , "t2");
		t1.start();
		t2.start();
	}
}


















