package model;

public class Name {

	private String name;
	private String middleI;
	private String lastName;
	
	public Name(String name, String middleI, String lastName) {
		this.name = name;
		this.middleI = middleI;
		this.lastName = lastName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMiddleI() {
		return middleI;
	}
	public void setMiddleI(String middleI) {
		this.middleI = middleI;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
