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















