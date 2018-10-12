# 中断线程
通过interupt()方法来中断本线程。中断线程可以分为两种，一种是中断阻塞状态的线程，一种是中断运行状态的线程。

如果中断阻塞状态的线程，首先中断标记会被设置为true，由于是阻塞线程，那么中断标记会被立即清除（设置为false），并且抛出一个InterruptedException异常，中断线程。

如果中断运行状态的线程，那么中断标记会被设置为true，中断线程。

中断一个已终止的线程是不会有任何操作的。

**例子：中断阻塞线程**

```
package thread;

/**
 * 中断线程
 * 调用线程实例的interrupt()方法可以中断本线程。
 * 如果当前线程处于阻塞状态（通过调用sleep,wait,join方法），那么会将中断标记设置为true，但是由于该线程已经是阻塞状态，所以中断标记又会被立即清除（设置为false），并抛出一个InterruptedException异常
 * 如果当前线程处于运行状态，那么会将中断标记设置为true，中断本线程
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
				System.out.println(getName() + "执行了" + i + "次" + getState());
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
			System.out.println(t1.getName() + "线程开始执行" + t1.getState());
			
			System.out.println(Thread.currentThread().getName() + "线程休眠300毫秒");
			Thread.sleep(300);
			
			t1.interrupt();
			
			Thread.sleep(300);
			
			System.out.println(t1.getName() + t1.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
```
打印的结果为：

```
t1线程开始执行RUNNABLE
main线程休眠300毫秒
t1执行了1次RUNNABLE
t1执行了2次RUNNABLE
t1RUNNABLE
t1TERMINATED
```
我们可以看到，最后的t1线程是终止的状态

**例子：中断运行状态的线程**

```
package thread;
/**
 * 中断线程
 * 中断运行状态的线程
 * @author andychen
 *
 */

class InterruptedThread2 extends Thread {
	
	public InterruptedThread2 (String name) {
		super(name);
	}
	
	public void run () {
		int i = 0;
		while (!isInterrupted()) {
			i++;
			System.out.println(getName() + "执行了" + i + "次");
		}
	}
}

public class InterruptedThreadTest2 {
	public static void main (String[] args) {
		try {
			Thread t1 = new InterruptedThread2("t1");
			t1.start();
			System.out.println(Thread.currentThread().getName() + "线程休眠10毫秒");
			Thread.sleep(10);
			t1.interrupt();
			Thread.sleep(10);
			System.out.println(t1.getName() + "线程" + t1.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
```
