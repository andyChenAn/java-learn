package thread;

class SleepThread2 extends Thread {
	public SleepThread2 (String name) {
		super(name);
	}
	public void run () {
		for (int i = 0 ; i < 5 ; i++) {
			System.out.println(getName() + "执行了" + i + "次");
		}
	}
}

public class SleepThreadTest2 {
	public static void main (String[] args) {
		Thread t1 = new SleepThread2("t1");
		Thread t2 = new SleepThread2("t2");
		Thread t3 = new SleepThread2("t3");
		t1.start();
		t2.start();
		t3.start();
		for (int i = 0 ; i < 5 ; i++) {
			System.out.println(Thread.currentThread().getName() + "执行了" + i + "次");
		}
	}
}
