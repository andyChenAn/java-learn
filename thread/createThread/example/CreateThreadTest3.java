package thread;

public class CreateThreadTest3 {
	public static void main (String[] args) {
		CreateThread3 t1 = new CreateThread3();
		CreateThread3 t2 = new CreateThread3();
		CreateThread3 t3 = new CreateThread3();
		t1.start();
		t2.start();
		t3.start();
	}
}

class CreateThread3 extends Thread {
	private int index = 5;
	public void run () {
		while (true) {
			if (this.index > 0) {
				System.out.println(Thread.currentThread().getName() + " index : " + this.index--);
			} else {
				break;
			}
		}
	}
}
