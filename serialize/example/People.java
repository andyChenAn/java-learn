package andyJava;

import java.io.Serializable;

public class People implements Serializable {
	private static final long serialVersionUID = 1L;
	private transient String name;
	private int age;
	
	public Integer getAge () {
		return age;
	}
	
	public void setAge (int age) {
		this.age = age;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException {
		s.defaultWriteObject();
		s.writeUTF(name);
		s.writeInt(age);
	}
	
	public void readObject (java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
		s.defaultReadObject();
		this.name = s.readUTF();
		this.age = s.readInt();
	}
}

























