package model;

public class Name {

	private String name;
	private String middleI;
	private String lastName;
	
	/**
	 * Constructor to create a Name
	 * @param name
	 * @param middleI
	 * @param lastName
	 */
	public Name(String name, String middleI, String lastName) {
		this.name = name;
		this.middleI = middleI;
		this.lastName = lastName;
	}
	
	/**
	 * return first name of name object
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * set first name of name object
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * reutrn middle name initial of name object
	 * @return
	 */
	public String getMiddleI() {
		return middleI;
	}
	
	/**
	 * set middle name initial of name object
	 * @param middleI
	 */
	public void setMiddleI(String middleI) {
		this.middleI = middleI;
	}
	
	/**
	 * return last name of name object
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * set last name of name object
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
