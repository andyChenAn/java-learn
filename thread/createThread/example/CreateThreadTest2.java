package thread;

public class CreateThreadTest2 {
	
	public static void main (String[] args) {
		
		// ͨ��ʵ��Runnable�ӿ��������߳�
		// �����Ǵ���һ���̶߳���ʱ��������Thread�������д���һ��ʵ��Runnable�ӿڵĶ��󣬲��һ����Դ���ڶ�����������Ϊ�̵߳�����
		Dog dog = new Dog();
		Thread td = new Thread(dog , "dog");
		td.start();
	}
	
}

class Dog implements Runnable {
	public void run () {
		System.out.println(Thread.currentThread().getName() + "�߳�ִ����");
	}
}
