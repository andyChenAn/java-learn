# 线程休眠
我们可以通过调用Thread.sleep()来让当前线程休眠，由运行状态转为阻塞状态。当超过指定的时间量后，当前线程又会由阻塞状态转为就绪状态。

```
package thread;

/**
 * 线程休眠
 * 我们可以通过调用Thread.sleep()来让当前线程休眠，由运行状态转为阻塞状态。当超过指定的时间量后，当前线程又会由阻塞状态转为就绪状态
 * @author andy
 *
 */

class SleepThread extends Thread {
	public void run () {
		System.out.println(getName() + "线程执行");
	}
}

public class SleepThreadTest {
	public static void main (String[] args) {
		Thread t1 = new SleepThread();
		t1.setName("t1");
		System.out.println(Thread.currentThread().getName() + "线程执行");
		t1.start();
		try {
			System.out.println(Thread.currentThread().getName() + "线程休眠2秒");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "线程继续执行");
	}
}
```
### sleep()和wait()的区别
1、sleep()方法：指的是休眠当前线程，将当前线程的状态由运行状态转为阻塞状态，但是并不会释放同步锁。

2、wait()方法：指的是将当前线程的状态由运行状态转为阻塞状态，并会释放同步锁。

当线程获取到同步锁后，再调用sleep()方法，并不会释放同步锁，其他线程要等到当前线程执行完了，释放同步锁后，才能获取同步锁，执行该线程。

```
package thread;

/**
 * sleep方法，不会释放同步锁
 * @author andychen
 *
 */

class SleepThread3 extends Thread {
	public SleepThread3 (String name) {
		super(name);
	}
	public void run () {
		synchronized (this) {
			for (int i = 0 ; i < 5 ; i++) {
				System.out.println(getName() + "执行了" + i + "次");
			}
		}
	}
}

public class SleepThreadTest3 {
	public static void main (String[] args) {
		Thread t1 = new SleepThread3("t1");
		t1.start();
		synchronized (t1) {
			for (int i = 0 ; i < 5 ; i++) {
				System.out.println(Thread.currentThread().getName() + "执行了" + i + "次");
			}
		}
	}
}
```
