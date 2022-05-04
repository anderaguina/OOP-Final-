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
	
	public void addStudent(String id, String name, String middleI, String lastName, String dob) {
		
		Name nameObj = new Name(name, middleI, lastName);
		
		Student student = new Student(id, nameObj, dob);
				
		derby.addStudent(student);
	}
	
	/**
	 * Get all students
	 * @param students
	 * @param index
	 */
	
	public ArrayList<Student> getAllStudents() {
		return derby.select_students();
	}
	
	public void removeStudent(String studentId) {
		derby.deleteStudent(studentId);
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
	
	public void registerMark(String studentId, String moduleName, int grade) {
		StudentModule module = new StudentModule(moduleName, grade);
						
		derby.addStudentModule(studentId, module);
	}
	
	public boolean fileExists() {
		File f = new File("t.tmp");
		return f.exists() && !f.isDirectory();
	}
	
	public ArrayList<StudentModule> findModulesForUser(String studentId) {
		return derby.selectStudentModules(studentId);
	}
}
