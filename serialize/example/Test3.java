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


















