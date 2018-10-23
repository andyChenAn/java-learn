package andyJava;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test1 {
	public static void main (String[] args) throws IOException, ClassNotFoundException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("obj.txt"));
		People people = new People();
		people.setName("andy");
		people.setAge(23);
		
		out.writeObject(people);
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("obj.txt"));
		People peopleGet = (People) in.readObject();
		System.out.println(peopleGet.getName());
		System.out.println(peopleGet.getAge());
		out.close();
		in.close();
	}
}






















