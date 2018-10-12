package thread;

/**
 * �ж��߳�
 * �����߳�ʵ����interrupt()���������жϱ��̡߳�
 * �����ǰ�̴߳�������״̬��ͨ������sleep,wait,join����������ô�Ὣ�жϱ������Ϊtrue���������ڸ��߳��Ѿ�������״̬�������жϱ���ֻᱻ�������������Ϊfalse�������׳�һ��InterruptedException�쳣
 * �����ǰ�̴߳�������״̬����ô�Ὣ�жϱ������Ϊtrue���жϱ��߳�
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
				System.out.println(getName() + "ִ����" + i + "��" + getState());
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
			System.out.println(t1.getName() + "�߳̿�ʼִ��" + t1.getState());
			
			System.out.println(Thread.currentThread().getName() + "�߳�����300����");
			Thread.sleep(300);
			
			t1.interrupt();
			
			Thread.sleep(300);
			
			System.out.println(t1.getName() + t1.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}




















