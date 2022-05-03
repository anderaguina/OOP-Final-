package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
	 private String id;  
	 // private String name;  
	 private String dob;
	 private Name name;
	 private ArrayList<StudentModule> modules;
	 
	 public Student(String id, Name name, String dob) {
		 this.id = id;
		 this.name = name;
		 this.dob = dob;
		 this.modules = new ArrayList<StudentModule>();
	 }

	public ArrayList<StudentModule> getModules() {
		return modules;
	}

	public void setModules(ArrayList<StudentModule> modules) {
		this.modules = modules;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
}
