# 线程让步
线程让步：指的是将当前线程的状态由运行状态，转为就绪状态，当前线程让出线程执行权，让其他线程可以获取线程执行权，但是当前线程也会去一同获取线程执行权，如果获取到了，那么又会执行当前线程。

```
package thread;

/**
 * yield 线程让步
 * 调用Thread.yield()方法，能够让当前线程由运行状态，转为就绪状态，同其他线程又一起竞争线程执行权。
 * @author andychen
 *
 */

class YieldThread extends Thread {
	public void run () {
		for (int i = 0 ; i < 5 ; i++) {
			System.out.println(getName() + "循环了" + i + "次");
			if (i % 2 == 0) {
				Thread.yield();
			}
		}
	}
}

public class YieldThreadTest {
	public static void main (String[] args) {
		
		Thread t1 = new YieldThread();
		t1.setName("t1");
		t1.start();
		for (int i = 0 ; i < 5 ; i++) {
			System.out.println(Thread.currentThread().getName() + "循环了" + i + "次");
			if (i % 2 == 0) {
				Thread.yield();
			}
		}
	}
}
```
打印的结果为：

```
t1循环了0次
main循环了0次
t1循环了1次
t1循环了2次
main循环了1次
main循环了2次
t1循环了3次
t1循环了4次
main循环了3次
main循环了4次

```
从上面的结果中，我们可以发现，当当前线程让出执行权后，也有可能又会获取到执行权，继续执行当前线程。

### yield()和wait()的区别
wait()方法：会将当前线程由运行状态转为阻塞状态，并释放同步锁。

yield()方法：会将当前线程由运行状态转为就绪状态，但是不会释放同步锁。


```
package thread;

/**
 * yield 线程让步
 * yield方法：会将当前线程由运行状态转为就绪状态，但不会释放同步锁
 * @author andy
 *
 */

public class YieldThreadTest3 {
	private static Object obj = new Object();
	public static void main (String[] args) {
		Thread t1 = new YieldThread3();
		Thread t2 = new YieldThread3();
		t1.setName("t1");
		t2.setName("t2");
		t1.start();
		t2.start();
	}
	
	static class YieldThread3 extends Thread {
		public void run () {
			synchronized (obj) {
				for (int i = 0 ; i < 5 ; i++) {
					System.out.println(getName() + "循环了" + i + "次");
					if (i % 2 == 2) {
						Thread.yield();
					}
				}
			}
		}
	}
}
```
打印的结果为：

```
t1循环了0次
t1循环了1次
t1循环了2次
t1循环了3次
t1循环了4次
t2循环了0次
t2循环了1次
t2循环了2次
t2循环了3次
t2循环了4次
```
上面代码，当获取了同步锁，那么yield并不会释放同步锁，所以要等到t1线程执行完之后，释放同步锁了，才执行t2线程。