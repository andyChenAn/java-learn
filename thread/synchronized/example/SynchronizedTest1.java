package thread;
/**
 * synchronized关键字
 * 当一个线程访问某个对象的synchronized方法或synchronized代码块时，其他线程访问该对象的synchronized方法或synchronized代码块将被阻塞
 * @author andychen
 *
 */
class SynchronizedThread implements Runnable {
	public void run () {
		synchronized (this) {
			try {
				for (int i = 0 ; i < 3 ; i++) {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + "循环" + i + "次");
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
