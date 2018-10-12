package thread;
/**
 * 中断线程
 * 中断运行状态的线程
 * @author andychen
 *
 */

class InterruptedThread2 extends Thread {
	
	public InterruptedThread2 (String name) {
		super(name);
	}
	
	public void run () {
		int i = 0;
		while (!isInterrupted()) {
			i++;
			System.out.println(getName() + "执行了" + i + "次");
		}
	}
}

public class InterruptedThreadTest2 {
	public static void main (String[] args) {
		try {
			Thread t1 = new InterruptedThread2("t1");
			t1.start();
			System.out.println(Thread.currentThread().getName() + "线程休眠2秒");
			Thread.sleep(10);
			t1.interrupt();
			Thread.sleep(10);
			System.out.println(t1.getName() + "线程" + t1.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
