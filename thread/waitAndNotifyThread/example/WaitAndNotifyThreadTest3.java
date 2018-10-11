package thread;

/**
 * 等待线程和唤醒线程
 * @author andychen
 *
 */

public class WaitAndNotifyThreadTest3 {
	
	private static Object obj = new Object();
	
	public static void main (String[] args) {
		
		Thread t1 = new WaitAndNotifyThread3();
		Thread t2 = new WaitAndNotifyThread3();
		Thread t3 = new WaitAndNotifyThread3();
		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");
		System.out.println(Thread.currentThread().getName() + "线程开始执行了");
		
		System.out.println(t1.getName() + "线程开始执行了");
		t1.start();
		System.out.println(t2.getName() + "线程开始执行了");
		t2.start();
		System.out.println(t3.getName() + "线程开始执行了");
		t3.start();
		try {
			System.out.println(Thread.currentThread().getName() + "线程休眠3秒");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (obj) {
			System.out.println(Thread.currentThread().getName() + "线程调用notifyAll方法");
			obj.notifyAll();
		}
	}
	
	static class WaitAndNotifyThread3 extends Thread {
		public void run () {
			synchronized (obj) {
				try {
					System.out.println(Thread.currentThread().getName() + "线程wait");
					obj.wait();
					System.out.println(Thread.currentThread().getName() + "线程 continue");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
