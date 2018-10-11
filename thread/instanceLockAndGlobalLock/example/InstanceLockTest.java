package thread;
/**
 * 实例锁
 * 当两个线程，访问同一个实例锁时，一个线程获得实例锁，另一个线程必须等待，直到该线程释放实例锁为止
 * @author andychen
 *
 */

class InstanceLcok {
	public void synchronizedMethod1 () {
		synchronized (this) {
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
	public void synchronizedMethod2 () {
		synchronized (this) {
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


















