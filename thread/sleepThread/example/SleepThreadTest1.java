package thread;

/**
 * 线程休眠
 * 我们可以通过调用Thread.sleep()来让当前线程休眠，由运行状态转为阻塞状态。当超过指定的时间量后，当前线程又会由阻塞状态转为就绪状态
 * @author andy
 *
 */

class SleepThread1 extends Thread {
	public void run () {
		System.out.println(getName() + "线程执行");
	}
}

public class SleepThreadTest1 {
	public static void main (String[] args) {
		Thread t1 = new SleepThread1();
		t1.setName("t1");
		System.out.println(Thread.currentThread().getName() + "线程执行");
		t1.start();
		
		try {
			System.out.println(Thread.currentThread().getName() + "线程休眠2秒");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "线程继续执行");
	}
}
















