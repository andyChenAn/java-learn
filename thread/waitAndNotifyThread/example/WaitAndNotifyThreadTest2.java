package thread;

/**
 * 唤醒线程和等待线程
 * 我们也可以在调用wait()方法的时候，传入一个参数，即wait(long timeout)
 * 当等待的时间超过指定的时间，那么当前线程会被唤醒，或者其他线程获得该对象同步锁，并调用该对象的notify()或notifyAll()方法来唤醒当前线程
 * @author andychen
 *
 */

class WaitAndNotifyThread2 extends Thread {
	public void run () {
		
		synchronized (this) {
			try {
				System.out.println(Thread.currentThread().getName() + "线程执行");
				Thread.sleep(1000);
				notify();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class WaitAndNotifyThreadTest2 {
	public static void main (String[] args) {
		Thread t1 = new WaitAndNotifyThread2();
		t1.setName("t1");
		synchronized (t1) {
			try {
				System.out.println(Thread.currentThread().getName() + "线程开始执行了");
				t1.start();
				
				System.out.println(Thread.currentThread().getName() + "线程进入等待状态");
				t1.wait(3000);
				
				System.out.println(Thread.currentThread().getName() + "线程继续执行");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
















