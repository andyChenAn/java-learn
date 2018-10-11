# synchronized关键字
### synchronized原理
当我们调用某个对象的synchronized方法时，就获取了该对象的同步锁。不同线程对同步锁的访问时互斥的，也就是说，当一个线程获取了该对象的同步锁，其他线程访问该对象的同步锁的时候，会被阻塞，直到该线程释放了同步锁后，才能访问。

### synchronized方法和synchronized代码块
1、 synchronized方法：指的是通过synchronized关键字来修饰的方法，比如：public void synchronized xx () {}

2、synchronized代码块：指的是通过synchronized关键字修饰的代码块，比如:synchronized (this) {}。

### synchronized规则
**1、当一个线程访问某个对象的synchronized方法或synchronized代码块，其他线程对该对象的同步锁的访问将被阻塞。**

```
package thread;
/**
 * synchronized关键字
 * 当一个线程访问某个对象的synchronized方法或synchronized代码块时，其他线程访问该对象的synchronized方法或synchronized代码块将被阻塞
 * @author andychen
 *
 */
class SynchronizedThread implements Runnable {
	public void run () {
		synchronized (this) {
			try {
				for (int i = 0 ; i < 3 ; i++) {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + "循环" + i + "次");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class SynchronizedTest1 {
	public static void main (String[] args) {
		SynchronizedThread s1 = new SynchronizedThread();
		
		Thread t1 = new Thread(s1 , "t1");
		Thread t2 = new Thread(s1 , "t2");
		
		t1.start();
		t2.start();
		
	}
}
```
打印结果为：
```
t1循环0次
t1循环1次
t1循环2次
t2循环0次
t2循环1次
t2循环2次
```
我们可以发现，当t1线程获取了同步锁时，t2线程就会被阻塞，直到t1线程释放同步锁后，才能访问。所以我们看到t1线程先执行（即使该线程休眠，t2线程也是在等待t1释放同步锁后，再获取该对象的同步锁），然后再是t2线程执行。

**2、当一个线程访问该对象的synchronized方法或synchronized代码块，其他线程可以访问该对象的非synchronized方法或代码块。**

```
package thread;

/**
 * synchronized关键字
 * 当一个线程访问某个对象的synchronized方法或synchronized代码块时，其他线程可以访问该对象的非synchronized方法或代码块.
 * @author andychen
 *
 */

class Synchronized {
	// 同步方法
	public void synchronizedMethod () {
		synchronized (this) {
			for (int i = 0 ; i < 3 ; i++) {
				System.out.println(Thread.currentThread().getName() + " 循环了 " + i + "次");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	// 非同步方法
	public void nonsynchronizedMethod () {
		for (int i = 0 ; i < 3 ; i++) {
			System.out.println(Thread.currentThread().getName() + " 循环了 " + i + "次");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class SynchronizedTest2 {
	public static void main (String[] args) {
		Synchronized sync = new Synchronized();
		Thread t1 = new Thread(new Runnable() {
			public void run () {
				sync.synchronizedMethod();
			}
		} , "t1");
		Thread t2 = new Thread(new Runnable() {
			public void run () {
				sync.nonsynchronizedMethod();
			}
		} , "t2");
		
		t1.start();
		t2.start();
	}
}
```
打印结果为：

```
t2 循环了 0次
t1 循环了 0次
t2 循环了 1次
t1 循环了 1次
t1 循环了 2次
t2 循环了 2次
```
通过上面结果，我们发现，即使一个线程访问该对象的同步方法或代码块，其他线程也还是可以访问该对象的其他非同步方法或代码块。

**3、当一个线程访问某个对象的synchronized方法或synchronized代码块时，其他线程访问该对象的其他synchrozied方法或synchronized代码块也将被阻塞**

```
package thread;
/**
 * synchronized关键字
 * 当一个线程访问某个对象的synchronized方法或synchronized代码块时，其他线程访问该对象的其他synchronized方法或synchronized代码块将会被阻塞
 * @author andychen
 *
 */

class Synchronized2 {
	// 同步方法1
	public void synchronizedMethod1 () {
		synchronized (this) {
			for (int i = 0 ; i < 3 ; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " 循环了 " + i + "次");
			}
		}
	}
	// 同步方法2
	public void synchronizedMethod2 () {
		synchronized (this) {
			for (int i = 0 ; i < 3 ; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " 循环了 " + i + "次");
			}
		}
	}
}

public class SynchronizedTest3 {
	public static void main (String[] args) {
		Synchronized2 sync = new Synchronized2();
		Thread t1 = new Thread(new Runnable() {
			public void run () {
				sync.synchronizedMethod1();
			}
		} , "t1");
		Thread t2 = new Thread(new Runnable () {
			public void run () {
				sync.synchronizedMethod2();
			}
		} , "t2");
		
		t1.start();
		t2.start();
	}
}
```
打印结果为：

```
t1 循环了 0次
t1 循环了 1次
t1 循环了 2次
t2 循环了 0次
t2 循环了 1次
t2 循环了 2次
```
通过上面结果，我们发现，当t1线程访问对象的synchronized方法时，t1线程休眠，t2线程也是没有执行的，也是处于一个等待的状态，直到t1线程释放该对象的同步锁后，t2线程才执行。