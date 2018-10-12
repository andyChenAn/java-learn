package thread;

/**
 * 中断线程
 * 调用线程实例的interrupt()方法可以中断本线程。
 * 如果当前线程处于阻塞状态（通过调用sleep,wait,join方法），那么会将中断标记设置为true，但是由于该线程已经是阻塞状态，所以中断标记又会被立即清除（设置为false），并抛出一个InterruptedException异常
 * 如果当前线程处于运行状态，那么会将中断标记设置为true，中断本线程
 * @author Administrator
 *
 */

class InterruptedThread1 extends Thread {
	public void run () {
		try {
			int i = 0;
			while (!isInterrupted()) {
				Thread.sleep(110);
				i++;
				System.out.println(getName() + "执行了" + i + "次" + getState());
			}
		} catch (InterruptedException e) {
			System.out.println(getName() + getState());
			//e.printStackTrace();
		}
	}
}


public class InterruptedThreadTest1 {
	public static void main (String[] args) {
		try {
			Thread t1 = new InterruptedThread1();
			t1.setName("t1");
			t1.start();
			System.out.println(t1.getName() + "线程开始执行" + t1.getState());
			
			System.out.println(Thread.currentThread().getName() + "线程休眠300毫秒");
			Thread.sleep(300);
			
			t1.interrupt();
			
			Thread.sleep(300);
			
			System.out.println(t1.getName() + t1.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}




















