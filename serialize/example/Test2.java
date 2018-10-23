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

























