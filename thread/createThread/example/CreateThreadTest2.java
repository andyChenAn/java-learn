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
