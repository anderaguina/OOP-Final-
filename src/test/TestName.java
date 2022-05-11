package test;

import model.Name;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert.*;

public class TestName {

	/**
	 * Test Setters and getters
	 */
	@Test(timeout=10)
	public void testSetName() {
	    String firstName = "Test Name";
	    
	    Name name = new Name();
	    
	    name.setName(firstName);
	    
	    assertEquals(name.getName(), firstName);
	}
	
	@Test(timeout=10)
	public void setMiddleName() {
	    String middleName = "Test Middle Name";
	    
	    Name name = new Name();
	    
	    name.setMiddleI(middleName);
	    
	    assertEquals(name.getMiddleI(), middleName);
	}
	
	@Test(timeout=10)
	public void setLastName() {
	    String lastName = "Test Last Name";
	    
	    Name name = new Name();
	    
	    name.setLastName(lastName);
	    
	    assertEquals(name.getLastName(), lastName);
	}
	
	/**
	 * Test constructors
	 */
	
	public void defaultConstructor() {
		Name name = new Name();
		
		assertEquals(name.getName(), "");
		assertEquals(name.getMiddleI(), "");
		assertEquals(name.getLastName(), "");
	}
	
	public void testNameConstructor() {
		String firstName = "Name";
		String middleName = "Middle Name";
		String lastName = "Last Name";
		
		Name name = new Name(firstName, middleName, lastName);
		
		assertEquals(name.getName(), firstName);
		assertEquals(name.getMiddleI(), middleName);
		assertEquals(name.getLastName(), lastName);
	}
	
	
}
