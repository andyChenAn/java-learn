# join方法
该方法表示让主线程等待子线程执行完之后再执行。这里所说的主线程指的是创建子线程的线程。比如：

```
class Father extends Thread {
	public void run () {
		Thread son = new Son();
	}
}

class Son extends Thread {
	public void run () {
		
	}
}
```
例子：

```
package thread;

/**
 * join方法，主线程会等到子线程执行完之后再执行
 * @author andychen
 *
 */

class Father extends Thread {
	public Father (String name) {
		super(name);
	}
	public void run () {
		Thread son = new Son("sonThread");
		son.start();
		try {
			son.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0 ; i < 5 ; i++) {
			System.out.println(getName() + "执行了" + i + "次");
		}
	}
}

class Son extends Thread {
	public Son (String name) {
		super(name);
	}
	public void run () {
		for (int i = 0 ; i < 5 ; i++) {
			System.out.println(getName() + "执行了" + i + "次");
		}
		try {
			System.out.println(getName() + "休眠3秒");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class JoinThreadTest1 {
	public static void main (String[] args) {
		Thread father = new Father("fatherThread");
		father.start();
	}
}
```
打印的结果为：

```
sonThread执行了0次
sonThread执行了1次
sonThread执行了2次
sonThread执行了3次
sonThread执行了4次
sonThread休眠3秒
fatherThread执行了0次
fatherThread执行了1次
fatherThread执行了2次
fatherThread执行了3次
fatherThread执行了4次
```
