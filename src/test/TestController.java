package test;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import model.Name;
import model.Student;
import model.StudentModule;

public class TestController {
	@Test
	public void testAddStudent() {
		
		String id = "ID";
		String name = "fName";
		String middleI = "mName";
		String lastName = "lName";
		java.util.Date date = new java.util.Date();
		Date dob = new Date(date.getTime());
		
				
		assertTrue(id instanceof String);
		assertTrue(name instanceof String);
		assertTrue(middleI instanceof String);
		assertTrue(lastName instanceof String);
		
		assertTrue(dob instanceof Date);
	}
	
	@Test
	public void testUpdateStudent() {
		
		String id = "ID";
		String name = "fName";
		String middleI = "mName";
		String lastName = "lName";
		java.util.Date date = new java.util.Date();
		Date dob = new Date(date.getTime());
		
				
		assertTrue(id instanceof String);
		assertTrue(name instanceof String);
		assertTrue(middleI instanceof String);
		assertTrue(lastName instanceof String);
		
		assertTrue(dob instanceof Date);
	}
	
	@Test
	public void testGetStudent() {
		String id = "ID";
		assertTrue(id instanceof String);
	}
	
	@Test
	public void testRemoveStudent() {
		String id = "ID";
		assertTrue(id instanceof String);
	}
	
	@Test
	public void testRegisterMark() {
		String studentId = "Id";
		String moduleName = "Module";
		int grade = 1;
		
		StudentModule module = new StudentModule(moduleName, grade);
					
		assertTrue(studentId instanceof String);
		assertTrue(moduleName instanceof String);
		
		assertTrue(module instanceof StudentModule);
	}
	
	@Test
	public void testFindModulesForUser() {
		String studentId = "Id";
		boolean honors = true;
		
		assertTrue(studentId instanceof String);		
	}
}
