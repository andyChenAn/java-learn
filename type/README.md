# Java类型信息
运行时类型信息使得我们可以在程序运行时发现和使用类型信息。
### 什么是MTTI？
MTTI指的是在程序运行时保存其对象的类型信息的行为，用来标示一个计算机语言是否有能力在运行期保持或判别对象或变量的类型信息。
### 为什么需要MTTI？
MTTI使得我们在编译期就已经知道所有对象的类型信息了。而多态的实现就需要使用到MTTI，因为要想实现多态必须满足三个条件：1、要存在继承关系，2、子类要覆盖父类方法，3、父类引用指向子类对象。而多态又是面向对象编程的三个特性之一，所以我们需要MTTI。

```
package study;

import java.util.*;

abstract class Shape {
	void draw () {
		System.out.println(this + ".draw()");
	}
	abstract public String toString();
}

class Circle extends Shape {
	public String toString () {
		return "Circle";
	}
}

class Square extends Shape {
	public String toString () {
		return "Square";
	}
}

class Triangle extends Shape {
	public String toString () {
		return "Triangle";
	}
}


public class Test1 {
	public static void main (String[] args) {
		List<Shape> shapes = new ArrayList<>();
		shapes.add(new Circle());
		shapes.add(new Square());
		shapes.add(new Triangle());
		for (Shape shape : shapes) {
			shape.draw();
		}
	}
}
```
在上面的例子中，当把Shape对象放进List<Shape>中时，会进行向上转型，在向上转型为Shape的时候，也丢失了Shape对象的具体类型。但对List来说，它们只是Shape类的对象。

当在运行时调用draw方法时，会通过MTTI来识别一个对象的具体类型，这样就能知道调用哪个方法。

**编译器在编译时只会检查类型之间是否存在继承关系，有则通过；而在运行时就会检查它的真实类型，是则通过，所以当我们向上转型的时候，其实子类和父类是存在继承关系的，所以编译器会通过，不会报错**

```
package study;

import java.util.*;

class Father {
	
}

class Son {
	
}

public class Test1 {
	public static void main (String[] args) {
		Father son = new Son();
	}
}
// 编译时就报错了，因为在向上转型的时候，Father类和Son类没有继承关系
```

```
package study;

import java.util.*;

class Father {
	
}

class Son extends Father {
	
}

public class Test1 {
	public static void main (String[] args) {
		Father son = new Son();
	}
}
// 编译时不会报错，因为在向上转型的时候，Father类和Son类存在继承关系
```

```
package study;

import java.util.*;

class Father {
	void say () {
		System.out.println(this);
	}
}

class Son extends Father {
	void study () {
		System.out.println("study");
	}
}

public class Test1 {
	public static void main (String[] args) {
		Father son = new Son();
		son.say();
		son.study();   // 这里会报错
	}
}
```
上面的代码，调用study方法时会报错，而且是在编译时报错，主要是因为，Son对象向上转型为Father，虽然在Son类中存在study这个方法，但是在编译的时候，Father类中根本没有study这个方法，所以就会报“The method study() is undefined for the type Father”这个错误。而如果我们给Father类也添加一个study方法，那么这个时候就不会报错了，因为在编译的时候，发现Father类也有study这个方法，比如：

```
package study;

import java.util.*;

class Father {
	void say () {
		System.out.println(this);
	}
	void study () {
		System.out.println("father study");
	}
}

class Son extends Father {
	void study () {
		System.out.println("son study");
	}
}

public class Test1 {
	public static void main (String[] args) {
		Father son = new Son();
		son.say();
		son.study();  // 这里不会报错
	}
}
```
那么上面代码是到底是执行哪一个study方法呢？Father类中的study还是Son类中的study呢？这个时候，我们只能在运行时来确定对象的具体类型了（这里指的就是son对象），在运行时我们发现，son变量实际指向的就是通过new Son()创建的Son对象，所以运行时最终执行的是Son对象中的study方法，所以打印的结果会是"son study"。如果是Father对象，那么调用的就是Father对象中的study方法，这种方式，我们就叫做多态。
### Class对象
每个类都有一个Class对象。每当编写并编译了一个新类，就会产生一个Class对象（被保存在同名的.class文件中）。为了生存这个类的对象，java虚拟机使用类加载器来加载这个类。

所有的类都是在对其第一次使用时，动态的加载到java虚拟机中。当程序创建第一个对类的静态成员的引用时，就会加载这个类。

```
package study;

import java.util.*;

class Candy {
	static String name = "andy";
	static {
		System.out.println("loading Candy");
	}
}

public class Test1 {
	public static void main (String[] args) {
		String name = Candy.name;  // 引用Candy类的一个静态成员name
	}
}
```
当我们访问一个类的静态成员时，java虚拟机就会加载这个类。所以我们执行后面静态块中的语句。这里需要注意的是，如果访问类的静态不能改变的成员时，类是不会加载的。比如：

```
package study;

import java.util.*;

class Candy {
	static final String name = "andy";
	static {
		System.out.println("loading Candy");
	}
}

public class Test1 {
	public static void main (String[] args) {
		String name = Candy.name;
	}
}
```
上面代码是不会执行static语句块的。这将表明类根本是没有被加载的。因为访问的成员是始终不会改变的，所以根本不需要加载类。

##### 获取Class对象的几种方式
1、Class.forName("包名+类名")。

2、instance.getClass()方法返回一个Class对象。

3、使用类字面常量，比如：Candy.class来获取Class对象。
### 反射
反射允许运行中的java程序获取自身的信息，并且可以操作类或对象的内部属性。简而言之，通过反射，我们可以在运行时获得程序或程序集中每一个类型的成员和成员的信息。程序中一般的对象的类型都是在编译期就确定下来的，而 Java 反射机制可以动态地创建对象并调用其属性，这样的对象的类型在编译期是未知的。所以我们可以通过反射机制直接创建对象，即使这个对象的类型在编译期是未知的。

反射的核心是 JVM 在运行时才动态加载类或调用方法/访问属性，它不需要事先（写代码的时候或编译期）知道运行对象是谁。

```
package study;

import java.lang.reflect.*;
import java.util.*;

class Father {
	public String name = "andy";
	public int age = 22; 
	public String sayName () {
		return name;
	}
}


public class Test1 {
	public static void main (String[] args) {
		try {
		    // 通过Class.forName()来获取Class对象
			Class f = Class.forName("study.Father");
			Method[] methods = f.getMethods();
			Field[] fields = f.getFields();
			// 打印出Class对象所包含的内部方法
			for (Method m : methods) {
				System.out.println(m);
			}
			System.out.println("*******************分割线********************");
			for (Field field : fields) {
				System.out.println(field);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("can not find class");
		}
	}
}
```
利用反射来调用类中的方法
```
package study;

import java.lang.reflect.*;
import java.util.*;

class Father {
	public String name = "andy";
	public int age = 22; 
	public void sayName () {
		System.out.println(name);
	}
}

class Son extends Father {
	
}


public class Test1 {
	public static void main (String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		try {
			Class f = Class.forName("study.Father");
			Father father = (Father) f.newInstance();
			Method method = f.getMethod("sayName");
			method.invoke(father);
		} catch (ClassNotFoundException e) {
			System.out.println("can not find class");
		}
	}
}
```
