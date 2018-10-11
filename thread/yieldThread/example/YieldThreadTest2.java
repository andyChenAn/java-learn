package thread;

/**
 * yield和wait方法的区别
 * yield方法：会将当前线程由运行状态转为就绪状态，但不会释放同步锁
 * wait方法：会将当前线程由运行状态转为阻塞状态，并释放同步锁
 * @author andy
 *
 */

class YieldThread2 extends Thread {
	public void run () {
		for (int i = 0 ; i < 5 ; i++) {
			System.out.println(getName() + "循环了" + i + "次");
			if (i % 2 == 0) {
				Thread.yield();
			}
		}
	}
}

public class YieldThreadTest2 {
	public static void main (String[] args) {
		Thread t1 = new YieldThread2();
		Thread t2 = new YieldThread2();
		t1.setName("t1");
		t2.setName("t2");
		
		t1.start();
		t2.start();
	}
}








