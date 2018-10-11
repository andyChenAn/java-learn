package thread;

/**
 * join方法，主线程会等到子线程执行完之后再执行
 * @author andychen
 *
 */

class Father extends Thread {
	public Father (String name) {
		super(name);
	}
	public void run () {
		Thread son = new Son("sonThread");
		son.start();
		try {
			son.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0 ; i < 5 ; i++) {
			System.out.println(getName() + "执行了" + i + "次");
		}
	}
}

class Son extends Thread {
	public Son (String name) {
		super(name);
	}
	public void run () {
		for (int i = 0 ; i < 5 ; i++) {
			System.out.println(getName() + "执行了" + i + "次");
		}
		try {
			System.out.println(getName() + "休眠3秒");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class JoinThreadTest1 {
	public static void main (String[] args) {
		Thread father = new Father("fatherThread");
		father.start();
	}
}
