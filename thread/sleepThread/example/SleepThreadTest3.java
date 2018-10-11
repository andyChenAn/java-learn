package thread;

/**
 * sleep方法，不会释放同步锁
 * @author andychen
 *
 */

class SleepThread3 extends Thread {
	public SleepThread3 (String name) {
		super(name);
	}
	public void run () {
		synchronized (this) {
			for (int i = 0 ; i < 5 ; i++) {
				System.out.println(getName() + "执行了" + i + "次");
			}
		}
	}
}

public class SleepThreadTest3 {
	public static void main (String[] args) {
		Thread t1 = new SleepThread3("t1");
		t1.start();
		synchronized (t1) {
			for (int i = 0 ; i < 5 ; i++) {
				System.out.println(Thread.currentThread().getName() + "执行了" + i + "次");
			}
		}
	}
}












