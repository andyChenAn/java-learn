package thread;

/**
 * �����̺߳͵ȴ��߳�
 * ͨ��wait()�������õ�ǰ�̴߳��ڵȴ�״̬��ͨ��notify()���������ѵ�ǰ�߳�
 * ����ǰ�̡߳��ڵ���wait()ʱ������ӵ�иö����ͬ���������̵߳���wait()֮�󣬻��ͷŸ�����Ȼ��һֱ�ȴ�ֱ���������̡߳����ö����ͬ������notify()��notifyAll()������Ȼ�󣬸��̼߳����ȴ�ֱ�������»�ȡ���ö����ͬ��������Ȼ��Ϳ��Խ������С�
 * ����ǰ�̡߳�ָ������cpu���������е��̡߳�
 * @author andychen
 *
 */

class WaitAndNotifyThread extends Thread {
	public void run () {
		synchronized (this) {
			try {
				Thread.sleep(2000);
				// ����Ҳ���Բ��õ���notify��������Ϊ����ֻ�������̣߳�һ����main�̣߳�һ����t1�̣߳���t1�߳�ִ�����ˣ�Ҳ��û�������߳����������ͬ�����ˣ�����main�̻߳ᱻ���ѣ���ִ��
				notify();
				System.out.println(Thread.currentThread().getName() + "����notify�������ѵ�ǰ�߳�");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


public class WaitAndNotifyThreadTest1 {
	public static void main (String[] args) {
		Thread t1 = new WaitAndNotifyThread();
		t1.setName("t1");
		synchronized (t1) {
			try {
				System.out.println(Thread.currentThread().getName() + "��ʼִ����t1�߳�");
				t1.start();
				
				System.out.println(Thread.currentThread().getName() + "�̴߳��ڵȴ�״̬");
				t1.wait();
				
				System.out.println(Thread.currentThread().getName() + "�̼߳���ִ��");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}









