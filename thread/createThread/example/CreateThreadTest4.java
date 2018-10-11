package thread;

/**
 * 通过实现Runnable接口的方式来创建线程，该线程中的数据是可以被多个线程共享的
 * @author andychen
 *
 */
public class CreateThreadTest4 {
	public static void main (String[] args) {
		CreateThread4 createThread4 = new CreateThread4();
		Thread t1 = new Thread(createThread4);
		Thread t2 = new Thread(createThread4);
		Thread t3 = new Thread(createThread4);
		t1.start();
		t2.start();
		t3.start();
	}
}

class CreateThread4 implements Runnable {
	private int index = 5;
	public void run () {
		while (true) {
			if (this.index > 0) {
				System.out.println(Thread.currentThread().getName() + " index : " + index--);
			} else {
				break;
			}
		}
	}
}
