package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
	 private String id;  
	 // private String name;  
	 private String dob;
	 private Name name;
	 private ArrayList<StudentModule> modules;
	 
	 /**
	  * Constructor to create a Student
	  * @param id
	  * @param name
	  * @param dob
	  */
	 public Student(String id, Name name, String dob) {
		 this.id = id;
		 this.name = name;
		 this.dob = dob;
		 this.modules = new ArrayList<StudentModule>();
	 }
	 
	 /**
	  * Set Student Modules for Student object
	  * @param modules
	  */
	 public void setModules(ArrayList<StudentModule> modules) {
		 this.modules = modules;
	 }

	/**
	 * Return list of StudentModules objects
	 * @return
	 */
	public ArrayList<StudentModule> getModules() {
		return modules;
	}

	/**
	 * return Id for student
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * return name for student (object from Name class)
	 * @return
	 */
	public Name getName() {
		return name;
	}

	/**
	 * return DOB for student
	 * @return
	 */
	public String getDob() {
		return dob;
	}
	
}
