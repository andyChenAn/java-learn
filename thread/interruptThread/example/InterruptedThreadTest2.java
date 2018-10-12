package thread;
/**
 * �ж��߳�
 * �ж�����״̬���߳�
 * @author andychen
 *
 */

class InterruptedThread2 extends Thread {
	
	public InterruptedThread2 (String name) {
		super(name);
	}
	
	public void run () {
		int i = 0;
		while (!isInterrupted()) {
			i++;
			System.out.println(getName() + "ִ����" + i + "��");
		}
	}
}

public class InterruptedThreadTest2 {
	public static void main (String[] args) {
		try {
			Thread t1 = new InterruptedThread2("t1");
			t1.start();
			System.out.println(Thread.currentThread().getName() + "�߳�����2��");
			Thread.sleep(10);
			t1.interrupt();
			Thread.sleep(10);
			System.out.println(t1.getName() + "�߳�" + t1.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
