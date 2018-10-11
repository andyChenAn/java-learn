package thread;
/**
 * synchronized�ؼ���
 * ��һ���̷߳���ĳ�������synchronized������synchronized�����ʱ�������̷߳��ʸö��������synchronized������synchronized����齫�ᱻ����
 * @author andychen
 *
 */

class Synchronized2 {
	// ͬ������1
	public void synchronizedMethod1 () {
		synchronized (this) {
			for (int i = 0 ; i < 3 ; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " ѭ���� " + i + "��");
			}
		}
	}
	// ͬ������2
	public void synchronizedMethod2 () {
		synchronized (this) {
			for (int i = 0 ; i < 3 ; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " ѭ���� " + i + "��");
			}
		}
	}
}

public class SynchronizedTest3 {
	public static void main (String[] args) {
		Synchronized2 sync = new Synchronized2();
		Thread t1 = new Thread(new Runnable() {
			public void run () {
				sync.synchronizedMethod1();
			}
		} , "t1");
		Thread t2 = new Thread(new Runnable () {
			public void run () {
				sync.synchronizedMethod2();
			}
		} , "t2");
		
		t1.start();
		t2.start();
	}
}















