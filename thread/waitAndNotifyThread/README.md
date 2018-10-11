# 线程等待与唤醒
我们可以通过wait()方法来让当前线程处于等待状态，并且释放当前线程所持有的锁。

```
package thread;

/**
 * 唤醒线程和等待线程
 * 通过wait()方法来让当前线程处于等待状态，通过notify()方法来唤醒当前线程
 * “当前线程”在调用wait()时，必须拥有该对象的同步锁。该线程调用wait()之后，会释放该锁；然后一直等待直到“其它线程”调用对象的同步锁的notify()或notifyAll()方法。然后，该线程继续等待直到它重新获取“该对象的同步锁”，然后就可以接着运行。
 * “当前线程”指的是在cpu上正在运行的线程。
 * @author andychen
 *
 */

class WaitAndNotifyThread extends Thread {
	public void run () {
		synchronized (this) {
			try {
				Thread.sleep(2000);
				// 这里也可以不用调用notify方法，因为这里只有两个线程，一个是main线程，一个是t1线程，当t1线程执行完了，也就没有其他线程来竞争这个同步锁了，所以main线程会被唤醒，并执行
				notify();
				System.out.println(Thread.currentThread().getName() + "调用notify方法唤醒当前线程");
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
				System.out.println(Thread.currentThread().getName() + "开始执行了t1线程");
				t1.start();
				
				System.out.println(Thread.currentThread().getName() + "线程处于等待状态");
				t1.wait();
				
				System.out.println(Thread.currentThread().getName() + "线程继续执行");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
```
打印的结果为：

```
main开始执行了t1线程
main线程处于等待状态
t1调用notify方法唤醒当前线程
main线程继续执行
```
当调用对象的wait()方法时，必须先获得该对象的同步锁，调用该对象的wait()方法，会让当前线程处于等待状态，直到其他线程获得该对象的同步锁，并调用了该对象的notify()方法或notifyAll()方法。才会唤醒当前线程。

### wait方法中也可以传入一个时间的参数
我们也可以在调用wait()方法的时候，传入一个参数，即wait(longtimeout)当等待的时间超过指定的时间，那么当前线程会被唤醒
```
package thread;

/**
 * 唤醒线程和等待线程
 * 我们也可以在调用wait()方法的时候，传入一个参数，即wait(long timeout)
 * 当等待的时间超过指定的时间，那么当前线程会被唤醒
 * @author andychen
 *
 */

class WaitAndNotifyThread2 extends Thread {
	public void run () {
		System.out.println(Thread.currentThread().getName() + "线程一直不断地执行");
		// 无限循环
		while (true) {}
	}
}

public class WaitAndNotifyThreadTest2 {
	public static void main (String[] args) {
		Thread t1 = new WaitAndNotifyThread2();
		t1.setName("t1");
		synchronized (t1) {
			try {
				System.out.println(Thread.currentThread().getName() + "线程开始执行了");
				t1.start();
				
				System.out.println(Thread.currentThread().getName() + "线程进入等待状态");
				t1.wait(3000);
				
				System.out.println(Thread.currentThread().getName() + "线程继续执行");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
```
打印的结果：

```
main线程开始执行了
main线程进入等待状态
t1线程一直不断地执行
main线程继续执行
```
当然我们也可以通过其他线程获得该对象同步锁之后，调用该对象的notify方法来唤醒当前线程，这样就不需要等待3000毫秒之后，才唤醒当前线程

```
package thread;

/**
 * 唤醒线程和等待线程
 * 我们也可以在调用wait()方法的时候，传入一个参数，即wait(long timeout)
 * 当等待的时间超过指定的时间，那么当前线程会被唤醒，或者其他线程获得该对象同步锁，并调用该对象的notify()或notifyAll()方法来唤醒当前线程
 * @author andychen
 *
 */

class WaitAndNotifyThread2 extends Thread {
	public void run () {
		
		synchronized (this) {
			try {
				System.out.println(Thread.currentThread().getName() + "线程执行");
				Thread.sleep(1000);
				notify();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class WaitAndNotifyThreadTest2 {
	public static void main (String[] args) {
		Thread t1 = new WaitAndNotifyThread2();
		t1.setName("t1");
		synchronized (t1) {
			try {
				System.out.println(Thread.currentThread().getName() + "线程开始执行了");
				t1.start();
				
				System.out.println(Thread.currentThread().getName() + "线程进入等待状态");
				t1.wait(3000);
				
				System.out.println(Thread.currentThread().getName() + "线程继续执行");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
```
通过notifyAll方法来唤醒所有线程

```
package thread;

/**
 * 等待线程和唤醒线程
 * @author andychen
 *
 */

public class WaitAndNotifyThreadTest3 {
	
	private static Object obj = new Object();
	
	public static void main (String[] args) {
		
		Thread t1 = new WaitAndNotifyThread3();
		Thread t2 = new WaitAndNotifyThread3();
		Thread t3 = new WaitAndNotifyThread3();
		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");
		System.out.println(Thread.currentThread().getName() + "线程开始执行了");
		
		System.out.println(t1.getName() + "线程开始执行了");
		t1.start();
		System.out.println(t2.getName() + "线程开始执行了");
		t2.start();
		System.out.println(t3.getName() + "线程开始执行了");
		t3.start();
		try {
			System.out.println(Thread.currentThread().getName() + "线程休眠3秒");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (obj) {
			System.out.println(Thread.currentThread().getName() + "线程调用notifyAll方法");
			obj.notifyAll();
		}
	}
	
	static class WaitAndNotifyThread3 extends Thread {
		public void run () {
			synchronized (obj) {
				try {
					System.out.println(Thread.currentThread().getName() + "线程wait");
					obj.wait();
					System.out.println(Thread.currentThread().getName() + "线程 continue");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
```
