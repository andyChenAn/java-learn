package thread;

public class CreateThreadTest1 {
	
	public static void main (String[] args) {
		
		// ͨ���̳�Thread��ķ�ʽ�������߳�
		// ����Ҫ������������run�����������ڵ���
		CreateThread t1 = new CreateThread();
		CreateThread t2 = new CreateThread();
		t1.start();
		t2.start();
		
	}
	
}


class CreateThread extends Thread {
	public void run () {
		System.out.println(getName() + "�߳�������");
	}
}
