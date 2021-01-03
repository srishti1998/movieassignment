package com.crejo.movieassignment.pojo;


public class User {

	private User_Status user_Status =User_Status.USER;
	private String name;
	private int number_of_reviews =0;
	public User(String name) {
		this.name = name;
	
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber_of_reviews() {
		return number_of_reviews;
	}
	public void setNumber_of_reviews(int number_of_reviews) {
		this.number_of_reviews = number_of_reviews;
	}
	public User_Status getUser_Status() {
		return user_Status;
	}
	public void setUser_Status(User_Status user_Status) {
		this.user_Status = user_Status;
	}

	
	
}
