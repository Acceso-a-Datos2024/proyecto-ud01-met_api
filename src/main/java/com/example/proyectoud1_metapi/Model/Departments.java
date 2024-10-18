package com.example.proyectoud1_metapi.Model;

public class Departments {
	private String displayName;
	private int departmentId;

	public Departments() {
	}


	public Departments(String displayName, int departmentId) {
		this.displayName = displayName;
		this.departmentId = departmentId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return displayName; // Este es el texto que se mostrará en el ComboBox
	}

}
