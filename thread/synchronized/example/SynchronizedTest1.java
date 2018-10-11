package thread;
/**
 * synchronized�ؼ���
 * ��һ���̷߳���ĳ�������synchronized������synchronized�����ʱ�������̷߳��ʸö����synchronized������synchronized����齫������
 * @author andychen
 *
 */
class SynchronizedThread implements Runnable {
	public void run () {
		synchronized (this) {
			try {
				for (int i = 0 ; i < 3 ; i++) {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + "ѭ��" + i + "��");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class SynchronizedTest1 {
	public static void main (String[] args) {
		SynchronizedThread s1 = new SynchronizedThread();
		
		Thread t1 = new Thread(s1 , "t1");
		Thread t2 = new Thread(s1 , "t2");
		
		t1.start();
		t2.start();
		
	}
}
