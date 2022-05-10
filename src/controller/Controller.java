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
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;

import model.Student;
import model.StudentModule;
import model.Name;


public class Controller {
	
	// Create db connection object (connection and all the methdods to interact with the db)
	private DBConn derby = new DBConn();
	
	/**
	 * Method to generate a student object and send it to the db method that will insert to the db
	 * @param id
	 * @param name
	 * @param middleI
	 * @param lastName
	 * @param dob
	 */
	public void addStudent(String id, String name, String middleI, String lastName, Date dob) {
		
		Name nameObj = new Name(name, middleI, lastName);
		
		Student student = new Student(id, nameObj, dob);
				
		derby.addStudent(student);
	}
	
	/**
	 * Update student (name and dob as id is primary key and cannot be changed without impacting the tables that use the pk as fk (student modules)
	 * @param id
	 * @param name
	 * @param middleI
	 * @param lastName
	 * @param dob
	 */
	public void updateStudent(String id, String name, String middleI, String lastName, Date dob) {
		
		Name nameObj = new Name(name, middleI, lastName);
		
		Student student = new Student(id, nameObj, dob);
				
		derby.updateStudent(student);
	}
	
	/**
	 * Method that returns all the students to the view from the db
	 */
	public ArrayList<Student> getAllStudents() {
		return derby.select_students();
	}
	
	/**
	 * Method that returns the required student to the view from the db
	 * @param studentId
	 */
	public Student selectStudent(String studentId) {
		return derby.selectStudent(studentId);
	}
	
	/**
	 * Call method in the db to remove student by id
	 * @param studentId
	 */
	public void removeStudent(String studentId) {
		derby.deleteStudent(studentId);
	}
	
	
	/**
	 * Add a module linked to given user
	 * @param studentId 
	 * @param moduleName
	 * @param grade (should be integer from 0 to 100)
	 */
	public void registerMark(String studentId, String moduleName, int grade) {
		StudentModule module = new StudentModule(moduleName, grade);
						
		derby.addStudentModule(studentId, module);
	}
	
	/**
	 * Get modules linked to provided user, if honors is true return the modules where the student got more than 70
	 * @param studentId
	 * @param honors
	 * @return
	 */
	public ArrayList<StudentModule> findModulesForUser(String studentId, boolean honors) {		
		if (honors) {
			return derby.selectStudentModulesHonors(studentId, honors);
		} else {
			return derby.selectStudentModules(studentId);
		}		
	}
}
