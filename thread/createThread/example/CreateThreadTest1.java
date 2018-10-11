package thread;

public class CreateThreadTest1 {
	
	public static void main (String[] args) {
		
		// 通过继承Thread类的方式来创建线程
		// 并且要在子类中重新run方法，这样在调用
		CreateThread t1 = new CreateThread();
		CreateThread t2 = new CreateThread();
		t1.start();
		t2.start();
		
	}
	
}


class CreateThread extends Thread {
	public void run () {
		System.out.println(getName() + "线程启动了");
	}
}
