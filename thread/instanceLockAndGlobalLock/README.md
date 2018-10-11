# 实例锁和全局锁
### 什么是实例锁？
锁在一个实例对象上，如果用synchronized修饰符来表示的话，那么就是synchronized。

```
package thread;
/**
 * 实例锁
 * 当两个线程，访问同一个实例锁时，一个线程获得实例锁，另一个线程必须等待，直到该线程释放实例锁为止
 * @author andychen
 *
 */

class InstanceLcok {
	public void synchronizedMethod1 () {
		synchronized (this) {
			for (int i = 0 ; i < 3 ; i++) {
				try {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + "循环了" + i + "次");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void synchronizedMethod2 () {
		synchronized (this) {
			for (int i = 0 ; i < 3 ; i++) {
				try {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + "循环了" + i + "次");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

public class InstanceLockTest {
	public static void main (String[] args) {
		InstanceLcok instanceLock = new InstanceLcok();
		Thread t1 = new Thread(new Runnable () {
			public void run () {
				instanceLock.synchronizedMethod1();
			}
		} , "t1");
		Thread t2 = new Thread(new Runnable () {
			public void run () {
				instanceLock.synchronizedMethod2();
			}
		} , "t2");
		t1.start();
		t2.start();
	}
}
```
代码执行的结果为：

```
t1循环了0次
t1循环了1次
t1循环了2次
t2循环了0次
t2循环了1次
t2循环了2次
```
通过上面代码，可以发现，两个线程访问的都是同一个对象的同步锁。所以t1执行完了，t2才能执行。

### 什么是全局锁？
锁在一个类上，如果用synchronized修饰符来表示的话，那么就是static synchronized。无论实例化多少个实例对象，这些实例对象都是共享同一个锁的。

```
package thread;

/**
 * 全局锁
 * @author andychen
 *
 */

class GlobalLock {
	public static synchronized void synchronizedMethod1 () {
		for (int i = 0 ; i < 3 ; i++) {
			try {
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + "循环了" + i + "次");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static synchronized void synchronizedMethod2 () {
		for (int i = 0 ; i < 3 ; i++) {
			try {
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + "循环了" + i + "次");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class GlobalLockTest {
	public static void main (String[] args) {
		GlobalLock sync = new GlobalLock();
		Thread t1 = new Thread(new Runnable () {
			public void run () {
				sync.synchronizedMethod1();
			}
		} , "t1");
		Thread t2 = new Thread(new Runnable () {
			public void run () {
				sync.synchronizedMethod1();
			}
		} , "t2");
		
		t1.start();
		t2.start();
	}
}
```
打印的结果为：

```
t1循环了0次
t1循环了1次
t1循环了2次
t2循环了0次
t2循环了1次
t2循环了2次
```