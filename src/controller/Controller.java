package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Student;
import model.StudentModule;
import model.Name;


public class Controller {
	private DBConn derby = new DBConn();
	
	public void addStudent(ArrayList<Student> students, String id, String name, String middleI, String lastName, String dob) {
		
		Name nameObj = new Name(name, middleI, lastName);
		
		Student student = new Student(id, nameObj, dob);
		
		derby.add_student(student);
		
		// Refresh arraylist
		students.clear();
		students.addAll(derby.select_students());
	}
	
	public void removeStudent(ArrayList<Student> students, String index) {
		students.remove(Integer.parseInt(index));
	}
	
	public void save(ArrayList<Student> students) {
		try {
			FileOutputStream fos = new FileOutputStream("t.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(students);
			oos.close();
		} catch (IOException e) {
	        e.printStackTrace(); 
	    }
	}
	
	public void registerMark(String studentName, ArrayList<Student> students, String moduleName, int grade) {
		StudentModule module = new StudentModule(moduleName, grade);
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getName().getName() == studentName) {
				students.get(i).getModules().add(module);
				break;
			}
		}
	}
	
	public boolean fileExists() {
		File f = new File("t.tmp");
		return f.exists() && !f.isDirectory();
	}
	
	public ArrayList<StudentModule> findModulesForUser(ArrayList<Student> students, String studentName) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getName().getName() == studentName) {
				return students.get(i).getModules();
			}
		}
		
		return new ArrayList<StudentModule>() ;
	}
}
