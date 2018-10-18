package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

import java.util.List;

public class Lecture {
	public String notes;
	private List<String> material;
	public List<String> getMaterial() {
		return material;
	}
	public void setMaterial(List<String> material) {
		this.material = material;
	}
	
	
}
