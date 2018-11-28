# java数组

数组是一个复合数据类型，它是一系列有序数据的集合，它当中的每一个元素都具有相同的数据类型。我们可以通过“[]”来访问数组中的元素。

数组是一种效率最高的存储和随机访问对象引用序列的方式。对象数组和基本类型数组在使用上基本相同，唯一的区别就是对象数组保存的是引用，而基本类型数组保存的是基本类型的值。

### 返回一个数组

```java
package study;

import java.util.*;

public class Test3 {
	private static Random rand = new Random();
	static final String[] FLAVORS = {
		"Chocolate" , "Strawberry" , "Vanilla Fudge Swirl" , "Mint Chip" , "Mocha Almond Fudge" , "Rum Raisin" , "Praline Cream" , "Mud Pie"
	};
	public static String[] flavorSet (int n) {
		if (n > FLAVORS.length) {
			throw new IllegalArgumentException("Set too big");
		}
		String[] results = new String[n];
		boolean[] picked = new boolean[FLAVORS.length];
		for (int i = 0 ; i < n ; i++) {
			int t;
			do {
				t = rand.nextInt(FLAVORS.length);
			} while (picked[t]);
			results[i] = FLAVORS[t];
			picked[t] = true;
		}
		return results;
	}
	public static void main (String[] args) {
		for (int i = 0 ; i < 7 ; i++) {
			System.out.println(Arrays.toString(flavorSet(3)));
		}
	}
}
```
### 多维数组
```java
package study;

import java.util.Arrays;

public class Test4 {
	public static void main (String[] args) {
		int[][] a = {
			{1 , 2 , 3},
			{4 , 5 , 6}
		};
		System.out.println(Arrays.deepToString(a));
	}
}
```
### 数组与泛型
我们在实例化数组的时候，不能实例化居然参数化类型的数组。因为类型擦除会移除掉参数类型信息，而数组必须知道它们所持有的确切类型，以强制保证类型安全。

但是，可以参数化数组本身的类型，比如：

```java
package study;

import java.util.Arrays;

class ClassParameter<T> {
	public T[] f (T[] arg) {
		return arg;
	}
}

class MethodParameter {
	public static <T> T[] f (T[] arg) {
		return arg;
	}
}

public class Test5 {
	public static void main (String[] args) {
		Integer[] ints = {1 , 2 , 3 , 4 , 5};
		Double[] doubles = {1.1 , 2.2 , 3.3 , 4.4 , 5.5};
		Integer[] ints2 = new ClassParameter<Integer>().f(ints);
		Double[] doubles2 = new ClassParameter<Double>().f(doubles);
		ints2 = MethodParameter.f(ints);
		doubles2 = MethodParameter.f(doubles);
		System.out.println(Arrays.toString(ints2));
		System.out.println(Arrays.toString(doubles2));
	}
}
```