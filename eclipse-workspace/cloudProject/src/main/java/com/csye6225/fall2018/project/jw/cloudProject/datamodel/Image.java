package com.csye6225.fall2018.project.jw.cloudProject.datamodel;

public class Image {
	public String name;
	public int imageNumber;
	private String imageURL;
	public Image() {
	}
	public Image(String name) {
		
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}
