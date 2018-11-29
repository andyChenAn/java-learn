# ArrayList
ArrayList其实就是一个动态数组，与java的数组相比，它的容量是可以动态增长的。从源码中我们可以看到ArrayList中的元素都是保存在elementData中。而elementData是一个数组：Object[]。所以我们可以看到，其实ArrayList底层也就是使用数组来存放数据。

ArrayList实现了List的接口，是一个数组队列，提供了相关的对数组元素进行增删改查的功能。

ArrayList实现了RandomAccess接口，表示提供了随机访问功能。

ArrayList实现了Cloneable接口，表示ArrayList可以被克隆。

ArrayList实现了Serializable接口，表示ArrayList可以序列化。
### 我们来比较一下ArrayList的三种遍历方式访问元素
我们可以通过for循环，iterator，get来访问ArrayList列表中的元素。比如：

```java
package study;

import java.util.*;

public class Test1 {
	public static void main (String[] args) {
		ArrayList list = new ArrayList();
		for (int i = 0 ; i < 100000000 ; i++) {
			list.add(1);
		}
		iteratorForIterator(list);
		iteratorForFor(list);
		iteratorForRandomAccess(list);
	}
	public static void iteratorForRandomAccess (ArrayList list) {
		long startTime;
		long endTime;
		startTime = System.currentTimeMillis();
		for (int i = 0 ; i < list.size() ; i++) {
			list.get(i);
		}
		endTime = System.currentTimeMillis();
		long diff = endTime - startTime;
		System.out.println("随机访问的时间为：" + diff + " 毫秒");
	}
	public static void iteratorForIterator (ArrayList list) {
		long startTime;
		long endTime;
		startTime = System.currentTimeMillis();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			it.next();
		}
		endTime = System.currentTimeMillis();
		long diff = endTime - startTime;
		System.out.println("通过迭代器访问元素的时间为：" + diff + " 毫秒");
	}
	public static void iteratorForFor (ArrayList list) {
		long startTime;
		long endTime;
		startTime = System.currentTimeMillis();
		for (Object i : list) {
			
		}
		endTime = System.currentTimeMillis();
		long diff = endTime - startTime;
		System.out.println("通过for循环访问元素的时间为：" + diff + " 毫秒");
	}
}
```
打印结果为：

```
通过迭代器访问元素的时间为：9 毫秒
通过for循环访问元素的时间为：11 毫秒
随机访问的时间为：4 毫秒

```
通过上面代码中，我们可以发现，通过get的方式随机访问元素的效率最高。
### toArray()问题
当我们调用toArray()方法，将ArrayList列表转为数组时，会出现异常，比如：

```java
public class Test1 {
	public static void main (String[] args) {
		ArrayList list = new ArrayList();
		for (int i = 0 ; i < 5 ; i++) {
			list.add(i);
		}
		Integer[] ints = (Integer[]) list.toArray();
		System.out.println(Arrays.toString(ints));
	}
}
```
打印结果：

```
Exception in thread "main" java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
	at study.Test1.main(Test1.java:11)
```
我们发现这个会报错，意思就是说调用toArray()方法返回的是一个Object[]类型数组，不能强制转换为Integer[]类型数组。这个时候我们应该调用toArray(T[] a)方法。比如：

```java
public class Test1 {
	public static void main (String[] args) {
		ArrayList list = new ArrayList();
		for (int i = 0 ; i < 5 ; i++) {
			list.add(i);
		}
		Integer[] ints = (Integer[]) list.toArray(new Integer[0]);
		System.out.println(Arrays.toString(ints));
	}
}
```
