package thread;

/**
 * synchronized关键字
 * 当一个线程访问某个对象的synchronized方法或synchronized代码块时，其他线程可以访问该对象的非synchronized方法或代码块.
 * @author andychen
 *
 */

class Synchronized {
	// 同步方法
	public void synchronizedMethod () {
		synchronized (this) {
			for (int i = 0 ; i < 3 ; i++) {
				System.out.println(Thread.currentThread().getName() + " 循环了 " + i + "次");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	// 非同步方法
	public void nonsynchronizedMethod () {
		for (int i = 0 ; i < 3 ; i++) {
			System.out.println(Thread.currentThread().getName() + " 循环了 " + i + "次");
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








