package thread;

/**
 * 守护线程和非守护线程
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
				System.out.println(getName() + "线程执行了" + i + "次");
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
				System.out.println(getName() + "线程执行了" + i + "次");
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
		System.out.println(Thread.currentThread().getName() + "线程执行");
	}
}












