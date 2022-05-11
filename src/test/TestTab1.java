package test;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import model.Student;
import view.Tab1;

public class TestTab1 {

	public void testUpdateTextArea() {
		TextArea textArea = new TextArea();
		ArrayList<Student> students = new ArrayList<Student>();
		Tab1 tab1 = new Tab1();
		
		StringBuilder fieldContent = new StringBuilder(""); 
		for (int i = 0; i < students.size(); i++) {
			assertTrue(students.get(i).getId() instanceof String);
			assertTrue( students.get(i).getName().getName() instanceof String);
			assertTrue(students.get(i).getDob() instanceof Date);
			
			fieldContent.append("Index: " + i + " | Student id: " + students.get(i).getId() + " | Student Name: "
				+ students.get(i).getName().getName() + " | DOB: " + students.get(i).getDob() + "\n");
		}
	}
	
	@Test (timeout=3000)
	public void updateChoiceBox() {
		ArrayList<Student> students = new ArrayList<Student>();
		
		for (int i = 0; i < students.size(); i++) {
			assertTrue(students.get(i).getId() instanceof String);
		}
		
		if (students.size() > 0) {
			assertTrue(students.get(0).getId() instanceof String);
		}
	}
}
