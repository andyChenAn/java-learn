package thread;

/**
 * 唤醒线程和等待线程
 * 通过wait()方法来让当前线程处于等待状态，通过notify()方法来唤醒当前线程
 * “当前线程”在调用wait()时，必须拥有该对象的同步锁。该线程调用wait()之后，会释放该锁；然后一直等待直到“其它线程”调用对象的同步锁的notify()或notifyAll()方法。然后，该线程继续等待直到它重新获取“该对象的同步锁”，然后就可以接着运行。
 * “当前线程”指的是在cpu上正在运行的线程。
 * @author andychen
 *
 */

class WaitAndNotifyThread extends Thread {
	public void run () {
		synchronized (this) {
			try {
				Thread.sleep(2000);
				// 这里也可以不用调用notify方法，因为这里只有两个线程，一个是main线程，一个是t1线程，当t1线程执行完了，也就没有其他线程来竞争这个同步锁了，所以main线程会被唤醒，并执行
				notify();
				System.out.println(Thread.currentThread().getName() + "调用notify方法唤醒当前线程");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


public class WaitAndNotifyThreadTest1 {
	public static void main (String[] args) {
		Thread t1 = new WaitAndNotifyThread();
		t1.setName("t1");
		synchronized (t1) {
			try {
				System.out.println(Thread.currentThread().getName() + "开始执行了t1线程");
				t1.start();
				
				System.out.println(Thread.currentThread().getName() + "线程处于等待状态");
				t1.wait();
				
				System.out.println(Thread.currentThread().getName() + "线程继续执行");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}









