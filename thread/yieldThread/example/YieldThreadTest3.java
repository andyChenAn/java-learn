package thread;

/**
 * yield 线程让步
 * yield方法：会将当前线程由运行状态转为就绪状态，但不会释放同步锁
 * @author andy
 *
 */

public class YieldThreadTest3 {
	private static Object obj = new Object();
	public static void main (String[] args) {
		Thread t1 = new YieldThread3();
		Thread t2 = new YieldThread3();
		t1.setName("t1");
		t2.setName("t2");
		t1.start();
		t2.start();
	}
	
	static class YieldThread3 extends Thread {
		public void run () {
			synchronized (obj) {
				for (int i = 0 ; i < 5 ; i++) {
					System.out.println(getName() + "循环了" + i + "次");
					if (i % 2 == 2) {
						Thread.yield();
					}
				}
			}
		}
	}
}












