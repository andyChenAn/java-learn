package thread;

/**
 * 全局锁
 * @author andychen
 *
 */

class GlobalLock {
	public static synchronized void synchronizedMethod1 () {
		for (int i = 0 ; i < 3 ; i++) {
			try {
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + "循环了" + i + "次");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static synchronized void synchronizedMethod2 () {
		for (int i = 0 ; i < 3 ; i++) {
			try {
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + "循环了" + i + "次");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class GlobalLockTest {
	public static void main (String[] args) {
		GlobalLock sync = new GlobalLock();
		Thread t1 = new Thread(new Runnable () {
			public void run () {
				sync.synchronizedMethod1();
			}
		} , "t1");
		Thread t2 = new Thread(new Runnable () {
			public void run () {
				sync.synchronizedMethod1();
			}
		} , "t2");
		
		t1.start();
		t2.start();
	}
}













