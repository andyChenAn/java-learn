package thread;

/**
 * �߳����ȼ�
 * @author andychen
 *
 */

class PriorityThread1 extends Thread {
	public PriorityThread1 (String name) {
		super(name);
	}
	
	public void run () {
		for (int i = 0 ; i < 10 ; i++) {
			System.out.println(getName() + "�̵߳����ȼ�Ϊ" + getPriority() + "ִ����" + i + "��");
		}
	}
}

public class PriorityThreadTest1 {
	public static void main (String[] args) {
		Thread t1 = new PriorityThread1("t1");
		Thread t2 = new PriorityThread1("t2");
		t1.setPriority(2);
		t2.setPriority(9);
		t1.start();
		t2.start();
	}
}

















