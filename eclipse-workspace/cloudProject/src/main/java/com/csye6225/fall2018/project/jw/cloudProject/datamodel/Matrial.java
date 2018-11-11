package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

import java.util.List;
import java.util.Set;

public class Matrial {
	private String id;
	private String matrialId;
	private String name;
	private String isCourse;
	private Set<String> allMetrial;
	public Matrial() {
		
	}
	
	public Matrial(String matrialId, String name, String isCourse,Set<String> allMetrial ) {
		this.matrialId = matrialId;
		this.name = name;
		this.isCourse = isCourse;
		this.setAllMetrial(allMetrial);
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getMatrialId() {
		return matrialId;
	}
	public void setMatrialId(String matrialId) {
		this.matrialId = matrialId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsCourse() {
		return isCourse;
	}
	public void setIsCourse(String isCourse) {
		this.isCourse = isCourse;
	}

	public Set<String> getAllMetrial() {
		return allMetrial;
	}

	public void setAllMetrial(Set<String> allMetrial) {
		this.allMetrial = allMetrial;
	}




	

}
