# 线程优先级
我们可以通过调用setPriority方法来设置线程的优先级。

```
package thread;

/**
 * 线程优先级
 * @author andychen
 *
 */

class PriorityThread1 extends Thread {
	public PriorityThread1 (String name) {
		super(name);
	}
	
	public void run () {
		for (int i = 0 ; i < 10 ; i++) {
			System.out.println(getName() + "线程的优先级为" + getPriority() + "执行了" + i + "次");
		}
	}
}

public class PriorityThreadTest1 {
	public static void main (String[] args) {
		Thread t1 = new PriorityThread1("t1");
		Thread t2 = new PriorityThread1("t2");
		t1.setPriority(2);
		t2.setPriority(9);
		t1.start();
		t2.start();
	}
}
```
打印的结果为：

```
t1线程的优先级为2执行了0次
t2线程的优先级为9执行了0次
t1线程的优先级为2执行了1次
t1线程的优先级为2执行了2次
t1线程的优先级为2执行了3次
t1线程的优先级为2执行了4次
t1线程的优先级为2执行了5次
t1线程的优先级为2执行了6次
t1线程的优先级为2执行了7次
t1线程的优先级为2执行了8次
t1线程的优先级为2执行了9次
t2线程的优先级为9执行了1次
t2线程的优先级为9执行了2次
t2线程的优先级为9执行了3次
t2线程的优先级为9执行了4次
t2线程的优先级为9执行了5次
t2线程的优先级为9执行了6次
t2线程的优先级为9执行了7次
t2线程的优先级为9执行了8次
t2线程的优先级为9执行了9次
```

