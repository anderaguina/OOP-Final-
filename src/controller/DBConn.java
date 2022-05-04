package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import model.Student;
import model.StudentModule;
import model.Name;

public class DBConn {	
	
	private Connection connectToDerbyDb(String url) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url);
			
		} catch (SQLException ex) {
			conn = null;
            ex.printStackTrace();
        }
		return conn;
	}

	/**
	 * Get students from derby db and parse the resulset to student arraylist
	 * @return
	 */
	public ArrayList<Student> select_students() {
		ArrayList<Student> students = new ArrayList<Student>();
		ResultSet res;
		
		try {
			Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students");
			
			Statement sta = conn.createStatement();
			
			String query = "SELECT * FROM Students";
			
			res = sta.executeQuery(query);
			
			while (res.next()) {
				// Create name object for the student
				Name name = new Name(res.getString("firstName"), res.getString("middlenamei"), res.getString("lastName"));
							
				Student student = new Student(res.getString("studentID"), name, res.getString("dob"));
				
				// Get modules for the student in this loop and append it to the student object
				student.setModules(selectStudentModules(res.getString("studentID")));
				
				// Add student to the arraylist (used to render the students)
				students.add(student);
			}
		     
		    res.close();
		} catch (SQLException ex) {
			res = null;
            ex.printStackTrace();
        }
		
		return students;
	}
	
	/**
	 * Get student by id
	 */
	public Student selectStudent(String studentId) {
		Student student = null;
		
		ResultSet res;
		
		try {
			Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students");
			
			Statement sta = conn.createStatement();
			
			String query = "SELECT * FROM Students where studentid='" + studentId + "'";
			
			res = sta.executeQuery(query);
			
			while (res.next()) {
				// Create name object for the student
				Name name = new Name(res.getString("firstName"), res.getString("middlenamei"), res.getString("lastName"));
							
				student = new Student(res.getString("studentID"), name, res.getString("dob"));
			}
		     
		    res.close();
		} catch (SQLException ex) {
			res = null;
            ex.printStackTrace();
        }
		
		return student;
	}
	
	/**
	 * Get student modules for a given student
	 */
	public ArrayList<StudentModule> selectStudentModules(String studentId) {
		ArrayList<StudentModule> studentModules = new ArrayList<StudentModule>();
		
		ResultSet res;
		
		try {
			Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students");
			
			Statement sta = conn.createStatement();
			
			String query = "SELECT * FROM StudentModules where studentid='" + studentId + "'";
			
			res = sta.executeQuery(query);
			
			while (res.next()) {
				StudentModule module = new StudentModule(res.getString("module"), res.getInt("grade"));
				
				studentModules.add(module);
			}
		     
		    res.close();
		} catch (SQLException ex) {
			res = null;
            ex.printStackTrace();
        }
		
		return studentModules;
	}
	
	/**
	 * Add students to db
	 * @return
	 */
	public void addStudent(Student student) {
		ArrayList<Student> students = new ArrayList<Student>();
		// ResultSet res;
		
		try {
			Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students");
			
			Statement sta = conn.createStatement();
			
			// String query = "INSERT INTO Students (STUDENTID, FIRSTNAME, MIDDLENAMEI, LASTNAME, DOB) VALUES"
				//	+ " ('" + student.getId() +"', '"+ student.getName() +"', 'Aguinagalde', 'Aguinagalde', '19-01-1995')";
			String query = "INSERT INTO Students (STUDENTID, FIRSTNAME, MIDDLENAMEI, LASTNAME, DOB) VALUES"
					+ "('"+ student.getId()+"', '"+ student.getName().getName() +"', '" + student.getName().getMiddleI() +"', '" + student.getName().getLastName() +"', '" +student.getDob() +"')";
			
			int insertedRows = sta.executeUpdate(query);
			
		} catch (SQLException ex) {
            ex.printStackTrace();
        }
		
	}
	
	/**
	 * Delete student from db
	 * @return
	 */
	public void deleteStudent(String studentId) {
		ArrayList<Student> students = new ArrayList<Student>();
		// ResultSet res;
		
		try {
			Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students");
			
			Statement sta = conn.createStatement();
			
			// String query = "INSERT INTO Students (STUDENTID, FIRSTNAME, MIDDLENAMEI, LASTNAME, DOB) VALUES"
				//	+ " ('" + student.getId() +"', '"+ student.getName() +"', 'Aguinagalde', 'Aguinagalde', '19-01-1995')";
			String query = "DELETE FROM STUDENTS WHERE STUDENTID = '" + studentId + "'";
			
			int insertedRows = sta.executeUpdate(query);
			
		} catch (SQLException ex) {
            ex.printStackTrace();
        }
		
	}
	
	/**
	 * Get students from derby db and parse the resulset to student arraylist
	 * @return
	 */
	public void addStudentModule(String studentId, StudentModule module) {
		ArrayList<Student> students = new ArrayList<Student>();
		// ResultSet res;
		
		try {
			Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students");
			
			Statement sta = conn.createStatement();

			String query = "INSERT INTO StudentModules (STUDENTID, MODULE, GRADE) VALUES"
					+ "('"+ studentId+"', '"+ module.getModule() +"', " + module.getGrade() +")";
			
			int insertedRows = sta.executeUpdate(query);
			
		} catch (SQLException ex) {
            ex.printStackTrace();
        }
		
	}
}
