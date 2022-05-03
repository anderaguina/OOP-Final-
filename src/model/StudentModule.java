package model;

import java.io.Serializable;

public class StudentModule implements Serializable {
	private String module;
	private int grade;
	
	public StudentModule(String module, int grade) {
		this.module = module;
		this.grade = grade;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
