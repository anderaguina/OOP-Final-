package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.ObservableList;
import model.Student;
import model.StudentModule;
import model.Name;

public class DBConn {	
	
	// Method to return a connection to the derby db (used only by the methods that will access the db)
	private Connection connectToDerbyDb(String url) {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url);
			return conn;
		} catch (SQLException ex) {
			// System.out.println("EXCEPTION => " +ex);
			return null;
        }
	}

	/**
	 * Get students from derby db and parse the resulset to student arraylist
	 * @return
	 */
	public ArrayList<Student> select_students() {
		ArrayList<Student> students = new ArrayList<Student>();
		ResultSet res;
		
		try {
			// Open connection to derby
			Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students");
			
			Statement sta = conn.createStatement();
			
			// Define query
			String query = "SELECT * FROM Students";
			
			res = sta.executeQuery(query);
			
			while (res.next()) {
				// Create name object for the student
				Name name = new Name(res.getString("firstName"), res.getString("middlenamei"), res.getString("lastName"));
				
				
							
				Student student = new Student(res.getString("studentID"), name, res.getDate("dob"));
				
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
			// Open connection to derby
			Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students");
			
			Statement sta = conn.createStatement();
			
			// Define query
			String query = "SELECT * FROM Students where studentid='" + studentId + "'";
			
			res = sta.executeQuery(query);
			
			while (res.next()) {
				// Create name object for the student
				Name name = new Name(res.getString("firstName"), res.getString("middlenamei"), res.getString("lastName"));
											
				student = new Student(res.getString("studentID"), name, res.getDate("dob"));
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
			// Open connection to derby
			Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students");
			
			Statement sta = conn.createStatement();
			
			// Define query
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
	 * Select modules where grade > 70%
	 * @param studentId
	 * @param honors
	 * @return
	 */
	public ArrayList<StudentModule> selectStudentModulesHonors(String studentId, boolean honors) {
		ArrayList<StudentModule> studentModules = new ArrayList<StudentModule>();
		
		ResultSet res;
		
		try {
			// Open connection to derby
			Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students");
			
			Statement sta = conn.createStatement();
			
			// Define query
			String query = "SELECT * FROM StudentModules where studentid='" + studentId + "' and grade > 70";
			
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
		try {
			// Open connection to derby
			Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students");
			
			Statement sta = conn.createStatement();
			
			// Define query
			String query = "INSERT INTO Students (STUDENTID, FIRSTNAME, MIDDLENAMEI, LASTNAME, DOB) VALUES"
					+ "('"+ student.getId()+"', '"+ student.getName().getName() +"', '" + student.getName().getMiddleI() +"', '"
					+ student.getName().getLastName() +"', '" +student.getDob() +"')";
			
			int insertedRows = sta.executeUpdate(query);
			
		} catch (SQLException ex) {
            ex.printStackTrace();
        }
		
	}
	
	/**
	 * Update student in db
	 * @param student
	 */
	public void updateStudent(Student student) {
		try {
			// Open connection to derby
			Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students");
			
			Statement sta = conn.createStatement();
			
			// Define query
			String query = "UPDATE Students set FIRSTNAME = '" + student.getName().getName() + "', MIDDLENAMEI = '" + student.getName().getMiddleI() +
					"', LASTNAME = '" + student.getName().getLastName() + "', DOB = '" + student.getDob() +"' WHERE STUDENTID = '" + student.getId() + "'";
			
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
		
		
		try {
			// Open connection to derby
			Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students");
			
			Statement sta = conn.createStatement();
			
			// Define query
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
			// Open connection to derby
			Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students");
			
			Statement sta = conn.createStatement();

			// Define query
			String query = "INSERT INTO StudentModules (STUDENTID, MODULE, GRADE) VALUES"
					+ "('"+ studentId+"', '"+ module.getModule() +"', " + module.getGrade() +")";
			
			int insertedRows = sta.executeUpdate(query);
			
		} catch (SQLException ex) {
            ex.printStackTrace();
        }
		
	}
	
	/**
	 * Return true if we can connect to the db false if we can't
	 * @return
	 */
	public boolean dbExists() {
		Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students");
				
		if (conn != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Function to create the db and the tables
	 */
	public void createDatabase() {
		
		try {
			// Create db and open connection to it
			Connection conn = connectToDerbyDb("jdbc:derby:C://Users/aguin/Desktop/CIT/Students; create=True");
			
			Statement sta = conn.createStatement();

			// Define query
			String createStudentsQuery = "CREATE TABLE Students (studentID varchar(20), firstName varchar(10), middlenameI varchar(20), lastName varchar(20), dob DATE, PRIMARY KEY (studentID))";
			String createStudentModulesQuery = "CREATE TABLE StudentModules (module varchar(20), grade INTEGER, studentID varchar(20) references students(studentID) ON DELETE CASCADE)";

			int countStudents = sta.executeUpdate(createStudentsQuery);
			int countStudentModules = sta.executeUpdate(createStudentModulesQuery);
			
		} catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
}
