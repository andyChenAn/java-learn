package thread;

/**
 * synchronized�ؼ���
 * ��һ���̷߳���ĳ�������synchronized������synchronized�����ʱ�������߳̿��Է��ʸö���ķ�synchronized����������.
 * @author andychen
 *
 */

class Synchronized {
	// ͬ������
	public void synchronizedMethod () {
		synchronized (this) {
			for (int i = 0 ; i < 3 ; i++) {
				System.out.println(Thread.currentThread().getName() + " ѭ���� " + i + "��");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	// ��ͬ������
	public void nonsynchronizedMethod () {
		for (int i = 0 ; i < 3 ; i++) {
			System.out.println(Thread.currentThread().getName() + " ѭ���� " + i + "��");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class SynchronizedTest2 {
	public static void main (String[] args) {
		Synchronized sync = new Synchronized();
		Thread t1 = new Thread(new Runnable() {
			public void run () {
				sync.synchronizedMethod();
			}
		} , "t1");
		Thread t2 = new Thread(new Runnable() {
			public void run () {
				sync.nonsynchronizedMethod();
			}
		} , "t2");
		
		t1.start();
		t2.start();
	}
}








