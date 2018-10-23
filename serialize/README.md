# 对象序列化
java序列化指的是：将对象转换为字节序列的过程，而java反序列化指的是：将字节序列转换为目标对象的过程。

### 怎么进行对象序列化？
通过ObjectOutputStream和ObjectInputStream这两个类来实现。

调用ObjectOutputStream对象的writeObject方法将对象以二进制的方式写入。

调用ObjectInputStream对象的readObject方法，从输入流中读取二进制数据，并转为对象。

```
package andyJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Test2 {
	public static void main (String[] args) throws IOException, ClassNotFoundException {
		System.out.println("序列化对象开始");
		Student student = new Student("andy" , 23 , "160149808@qq.com");
		FileOutputStream fileOutputStream = new FileOutputStream("data.txt");
		ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
		out.writeObject(student);
		System.out.println("序列化对象完成");
		
		System.out.println("反序列化对象开始");
		FileInputStream fileInputStream = new FileInputStream("data.txt");
		ObjectInputStream in = new ObjectInputStream(fileInputStream);
		Student s = (Student) in.readObject();
		System.out.println("反序列化对象数据为：");
		System.out.println(s.getName());
		System.out.println(s.getAge());
		System.out.println(s.getEmail());
	}
}

class Student implements Serializable {
	private String name;
	private transient int age;
	private transient String email;
	
	public Student (String name , int age , String email) {
		this.name = name;
		this.age = age;
		this.email = email;
	}
	
	public String getName () {
		return name;
	}
	
	public int getAge () {
		return age;
	}
	
	public String getEmail () {
		return email;
	}
}
```
### transient关键字
当我们有些类的实例成员不需要参与序列化过程的时候，我们可以使用transient关键字来修饰对象的这个字段。

```
package andyJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Test2 {
	public static void main (String[] args) throws IOException, ClassNotFoundException {
		System.out.println("序列化对象开始");
		Student student = new Student("andy" , 23 , "160149808@qq.com");
		FileOutputStream fileOutputStream = new FileOutputStream("data.txt");
		ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
		out.writeObject(student);
		System.out.println("序列化对象完成");
		
		System.out.println("反序列化对象开始");
		FileInputStream fileInputStream = new FileInputStream("data.txt");
		ObjectInputStream in = new ObjectInputStream(fileInputStream);
		Student s = (Student) in.readObject();
		System.out.println("反序列化对象数据为：");
		System.out.println(s.getName());
		System.out.println(s.getAge());
		System.out.println(s.getEmail());
	}
}

class Student implements Serializable {
	private String name;
	private transient int age;
	private transient String email;
	
	public Student (String name , int age , String email) {
		this.name = name;
		this.age = age;
		this.email = email;
	}
	
	public String getName () {
		return name;
	}
	
	public int getAge () {
		return age;
	}
	
	public String getEmail () {
		return email;
	}
}
```
打印的结果为：

```
序列化对象开始
序列化对象完成
反序列化对象开始
反序列化对象数据为：
andy
0
null
```
我们会发现当使用transient关键字来修饰类的实例成员时，该实例成员是不参与序列化过程的。

当然我们也可以通过自定义序列化操作来覆盖原有的序列化过程。我们只需要在实现Serializable接口的类上定义这两个方法即可：

```
private void writeObject (ObjectOutputStream s) {
    
}

private void readObject (ObjectInputStream s) {
    
}
```
比如这个例子：

```
package andyJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Test2 {
	public static void main (String[] args) throws IOException, ClassNotFoundException {
		System.out.println("序列化对象开始");
		Student student = new Student("andy" , 23 , "160149808@qq.com");
		FileOutputStream fileOutputStream = new FileOutputStream("data.txt");
		ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
		out.writeObject(student);
		System.out.println("序列化对象完成");
		
		System.out.println("反序列化对象开始");
		FileInputStream fileInputStream = new FileInputStream("data.txt");
		ObjectInputStream in = new ObjectInputStream(fileInputStream);
		Student s = (Student) in.readObject();
		System.out.println("反序列化对象数据为：");
		System.out.println(s.getName());
		System.out.println(s.getAge());
		System.out.println(s.getEmail());
	}
}

class Student implements Serializable {
	private String name;
	private transient int age;
	private transient String email;
	
	public Student (String name , int age , String email) {
		this.name = name;
		this.age = age;
		this.email = email;
	}
	
	public String getName () {
		return name;
	}
	
	public int getAge () {
		return age;
	}
	
	public String getEmail () {
		return email;
	}
	
	private void writeObject (ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeInt(age);
		s.writeUTF(email);
	}
	
	private void readObject (ObjectInputStream s) throws ClassNotFoundException, IOException {
		s.defaultReadObject();
		this.age = s.readInt();
		this.email = s.readUTF();
	}
}

```
打印的结果为：

```
序列化对象开始
序列化对象完成
反序列化对象开始
反序列化对象数据为：
andy
23
160149808@qq.com
```
关键字transient修饰的实例变量也会进行序列化，因为我们对writeObject和readObject方法进行了重写。

### 注意点：
1、在对象被序列化的时候，只会序列化对象的非静态成员变量，不能序列化任何成员方法和静态成员变量。

2、在对象被序列化的时候，如果一个对象的成员变量是一个对象（该对象实现了Serializable接口，如果没有实现Serializable接口，则会报错），那么这个对象的数据成员也会被保存。

```
package andyJava;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Test3 {
	public static void main (String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Student2 student2 = new Student2("andy");
		Student1 student1 = new Student1("jack" , student2);
		System.out.println("对象序列化开始");
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("andy.txt"));
		out.writeObject(student1);
		System.out.println("对象序列化结束");
		
		System.out.println("对象反序列化开始");
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("andy.txt"));
		Student1 s1 = (Student1) in.readObject();
		System.out.println(s1.getName());
		System.out.println(s1.getStudent2().getName());
	}
}

class Student1 implements Serializable {
	private String name;
	private Student2 s2;
	
	public Student1 (String name , Student2 s2) {
		this.name = name;
		this.s2 = s2;
	}
	
	public String getName () {
		return name;
	}
	
	public Student2 getStudent2 () {
		return s2;
	}
	
}

class Student2 implements Serializable {
	private String name;
	
	public Student2 (String name) {
		this.name = name;
	}
	
	public String getName () {
		return name;
	}
}
```
打印的结果为:

```
对象序列化开始
对象序列化结束
对象反序列化开始
jack
andy
```
