package thread;

/**
 * yield 线程让步
 * 调用Thread.yield()方法，能够让当前线程由运行状态，转为就绪状态，同其他线程又一起竞争线程执行权。
 * @author andychen
 *
 */

class YieldThread extends Thread {
	public void run () {
		for (int i = 0 ; i < 5 ; i++) {
			System.out.println(getName() + "循环了" + i + "次");
			if (i % 2 == 0) {
				Thread.yield();
			}
		}
	}
}

public class YieldThreadTest1 {
	public static void main (String[] args) {
		
		Thread t1 = new YieldThread();
		t1.setName("t1");
		t1.start();
		for (int i = 0 ; i < 5 ; i++) {
			System.out.println(Thread.currentThread().getName() + "循环了" + i + "次");
			if (i % 2 == 0) {
				Thread.yield();
			}
		}
	}
}
















