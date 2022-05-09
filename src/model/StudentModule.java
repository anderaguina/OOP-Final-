package model;

import java.io.Serializable;

public class StudentModule implements Serializable {
	private String module;
	private int grade;
	
	/**
	 * Constructor for StudentModules
	 * @param module
	 * @param grade
	 */
	public StudentModule(String module, int grade) {
		this.module = module;
		this.grade = grade;
	}

	/**
	 * Return module name
	 * @return
	 */
	public String getModule() {
		return module;
	}

	/**
	 * Return grade
	 * @return
	 */
	public int getGrade() {
		return grade;
	}
}
