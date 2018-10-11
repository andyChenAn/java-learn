package thread;
/**
 * synchronized关键字
 * 当一个线程访问某个对象的synchronized方法或synchronized代码块时，其他线程访问该对象的其他synchronized方法或synchronized代码块将会被阻塞
 * @author andychen
 *
 */

class Synchronized2 {
	// 同步方法1
	public void synchronizedMethod1 () {
		synchronized (this) {
			for (int i = 0 ; i < 3 ; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " 循环了 " + i + "次");
			}
		}
	}
	// 同步方法2
	public void synchronizedMethod2 () {
		synchronized (this) {
			for (int i = 0 ; i < 3 ; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " 循环了 " + i + "次");
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















