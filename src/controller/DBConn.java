package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import model.Student;
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
				Name name = new Name(res.getString("firstName"), res.getString("middlenamei"), res.getString("lastName"));
				Student student = new Student(res.getString("studentID"), name, res.getString("dob"));
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
	 * Get students from derby db and parse the resulset to student arraylist
	 * @return
	 */
	public ArrayList<Student> add_student(Student student) {
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
		
		return students;
	}
}
