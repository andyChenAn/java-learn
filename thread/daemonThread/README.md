# 守护线程和用户线程
用户线程：指的是我们平常常见出来的普通线程

守护线程：指的是用来服务用户线程的，当所有的用户线程都执行完毕后，只剩下守护线程的时候，JVM会直接退出，因为已经没有服务的用户线程了。

**守护线程和用户线程**

```
package thread;

/**
 * 守护线程和非守护线程
 * @author Administrator
 *
 */

class DaemonThread extends Thread {
	
	public DaemonThread (String name) {
		super(name);
	}
	
	public void run () {
		try {
			for (int i = 0 ; i < 500 ; i++) {
				Thread.sleep(1);
				System.out.println(getName() + "线程执行了" + i + "次");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

class NonDaemonThread extends Thread {
	public NonDaemonThread (String name) {
		super(name);
	}
	
	public void run () {
		try {
			for (int i = 0 ; i < 5 ; i++) {
				Thread.sleep(3);
				System.out.println(getName() + "线程执行了" + i + "次");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}


public class PriorityThreadTest2 {
	public static void main (String[] args) {
		Thread daemonThread = new DaemonThread("daemonThread");
		Thread nonDaemonThread = new NonDaemonThread("nonDaemonThread");
		daemonThread.setDaemon(true);
		daemonThread.start();
		nonDaemonThread.start();
		System.out.println(Thread.currentThread().getName() + "线程执行");
	}
}

```
打印的结果为：

```
main线程执行
daemonThread线程执行了0次
daemonThread线程执行了1次
nonDaemonThread线程执行了0次
daemonThread线程执行了2次
daemonThread线程执行了3次
daemonThread线程执行了4次
daemonThread线程执行了5次
nonDaemonThread线程执行了1次
daemonThread线程执行了6次
daemonThread线程执行了7次
nonDaemonThread线程执行了2次
daemonThread线程执行了8次
daemonThread线程执行了9次
daemonThread线程执行了10次
nonDaemonThread线程执行了3次
daemonThread线程执行了11次
daemonThread线程执行了12次
daemonThread线程执行了13次
daemonThread线程执行了14次
nonDaemonThread线程执行了4次
```
当没有用户线程了，只剩下守护线程的时候，java虚拟机就会直接退出。