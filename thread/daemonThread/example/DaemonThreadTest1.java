package thread;

/**
 * �ػ��̺߳ͷ��ػ��߳�
 * @author Administrator
 *
 */

class DaemonThread extends Thread {
	
	public DaemonThread (String name) {
		super(name);
	}
	
	public void run () {
		try {
			for (int i = 0 ; i < 500 ; i++) {
				Thread.sleep(1);
				System.out.println(getName() + "�߳�ִ����" + i + "��");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

class NonDaemonThread extends Thread {
	public NonDaemonThread (String name) {
		super(name);
	}
	
	public void run () {
		try {
			for (int i = 0 ; i < 5 ; i++) {
				Thread.sleep(3);
				System.out.println(getName() + "�߳�ִ����" + i + "��");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}


public class DaemonThreadTest1 {
	public static void main (String[] args) {
		Thread daemonThread = new DaemonThread("daemonThread");
		Thread nonDaemonThread = new NonDaemonThread("nonDaemonThread");
		daemonThread.setDaemon(true);
		daemonThread.start();
		nonDaemonThread.start();
		System.out.println(Thread.currentThread().getName() + "�߳�ִ��");
	}
}












