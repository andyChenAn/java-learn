# 生产消费模型

生产/消费者问题是个非常典型的多线程问题，涉及到的对象包括“生产者”、“消费者”、“仓库”和“产品”。他们之间的关系如下：

1、生产者仅仅在仓储未满时候生产，仓满则停止生产。

2、消费者仅仅在仓储有产品时候才能消费，仓空则等待。

3、当消费者发现仓储没产品可消费时候会通知生产者生产。

4、生产者在生产出可消费产品时候，应该通知等待的消费者去消费。

```
package thread;

/**
 * 生产消费模型：典型的多线程问题
 * @author andychen
 *
 */

// 仓库
class Depot1 {
	private int capacity;
	private int count = 0;
	
	public Depot1 (int capacity) {
		this.capacity = capacity;
	}
	
	public synchronized void produce (int val) {
		try {
			int left = val;
			while (left > 0) {
				while (count >= capacity) {
					wait();
				}
				int actualSize = ((left + count) > capacity) ? capacity - count : left;
				count += actualSize;
				left -= actualSize;
				System.out.println(Thread.currentThread().getName() + "count为：" + count);
				notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void consume (int val) {
		try {
			int left = val;
			while (left > 0) {
				while (count <= 0) {
					wait();
				}
				int actualSize = count < left ? count : left;
				count -= actualSize;
				left -= actualSize;
				System.out.println(Thread.currentThread().getName() + "count为：" + count);
				notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
}

// 生产者
class Producer1 {
	private Depot1 depot;
	
	public Producer1 (Depot1 depot) {
		this.depot = depot;
	}
	
	public void produce (final int val) {
		new Thread () {
			public void run () {
				depot.produce(val);
			}
		}.start();
	}
}

// 消费者
class Customer1 {
	private Depot1 depot;
	
	public Customer1 (Depot1 depot) {
		this.depot = depot;
	}
	
	public void consume (final int val) {
		new Thread () {
			public void run () {
				depot.consume(val);
			}
		}.start();
	}
}



public class ProduceAndConsumeThreadTest1 {
	public static void main (String[] args) {
		Depot1 depot = new Depot1(100);
		Producer1 p1 = new Producer1(depot);
		Customer1 c1 = new Customer1(depot);
		
		p1.produce(60);
		p1.produce(120);
		c1.consume(90);
		c1.consume(150);
		p1.produce(110);
		
	}
}
```
打印的结果为：

```
Thread-0count为：60
Thread-1count为：100
Thread-2count为：10
Thread-1count为：90
Thread-3count为：0
Thread-4count为：100
Thread-3count为：40
Thread-4count为：50
```
