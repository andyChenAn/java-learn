# 如何创建线程
在java中，一个类想要作为线程来使用，可以通过两种方式来实现。

1、继承Thread类，并重写run方法。

2、实现Runnable接口，并重写run方法。

### 通过继承Thread类的方式来创建线程

```
package thread;

public class CreateThreadTest1 {
	
	public static void main (String[] args) {
		
		// 通过继承Thread类的方式来创建线程
		// 并且要在子类中重写run方法
		CreateThread t1 = new CreateThread();
		CreateThread t2 = new CreateThread();
		t1.start();
		t2.start();
		
	}
	
}

class CreateThread extends Thread {
	public void run () {
		System.out.println(getName() + "线程执行了");
	}
}
```
### 通过实现Runnable接口来创建线程

```
package thread;

public class CreateThreadTest2 {
	
	public static void main (String[] args) {
		
		// 通过实现Runnable接口来创建线程
		// 当我们创建一个线程对象时，可以在Thread构造器中传入一个实现Runnable接口的对象，并且还可以传入第二个参数，作为线程的名称
		Dog dog = new Dog();
		Thread td = new Thread(dog , "dog");
		td.start();
	}
	
}


class Dog implements Runnable {
	public void run () {
		System.out.println(Thread.currentThread().getName() + "线程执行了");
	}
}
```
### 继承Thread类和实现Runnable接口这两种方式创建线程的区别
1、继承的方式创建的线程，不会共享数据，而通过实现Runnable接口的方式创建的线程，会共享数据。

2、java继承不支持多重继承，所以如果通过继承的方式来创建线程，那么该线程就不能再继承其他类，而通过实现Runnable接口的方式创建线程就不会有这样的问题。

所以尽量使用实现Runnable接口的方式来创建线程会好一点。

```
package thread;
// 通过继承Thread方法来实现线程
public class CreateThreadTest3 {
	public static void main (String[] args) {
		CreateThread3 t1 = new CreateThread3();
		CreateThread3 t2 = new CreateThread3();
		CreateThread3 t3 = new CreateThread3();
		t1.start();
		t2.start();
		t3.start();
	}
}

class CreateThread3 extends Thread {
	private int index = 5;
	public void run () {
		while (true) {
			if (this.index > 0) {
				System.out.println(Thread.currentThread().getName() + " index : " + this.index--);
			} else {
				break;
			}
		}
	}
}
```
某一次的打印结果为：

```
Thread-0 index : 5
Thread-2 index : 5
Thread-0 index : 4
Thread-1 index : 5
Thread-0 index : 3
Thread-2 index : 4
Thread-0 index : 2
Thread-1 index : 4
Thread-0 index : 1
Thread-2 index : 3
Thread-2 index : 2
Thread-2 index : 1
Thread-1 index : 3
Thread-1 index : 2
Thread-1 index : 1
```
我们发现index实例属性在三个线程中是单独存在的，并没有被三个线程所共享。


```
package thread;

/**
 * 通过实现Runnable接口的方式来创建线程，该线程中的数据是可以被多个线程共享的
 * @author andychen
 *
 */
public class CreateThreadTest4 {
	public static void main (String[] args) {
		CreateThread4 createThread4 = new CreateThread4();
		Thread t1 = new Thread(createThread4);
		Thread t2 = new Thread(createThread4);
		Thread t3 = new Thread(createThread4);
		t1.start();
		t2.start();
		t3.start();
	}
}

class CreateThread4 implements Runnable {
	private int index = 5;
	public void run () {
		while (true) {
			if (this.index > 0) {
				System.out.println(Thread.currentThread().getName() + " index : " + index--);
			} else {
				break;
			}
		}
	}
}
```
某一次的打印结果为：

```
Thread-0 index : 5
Thread-0 index : 2
Thread-0 index : 1
Thread-2 index : 3
Thread-1 index : 4
```
通过实现Runnable接口的方式来创建线程，该线程中的数据是可以被多个线程共享的