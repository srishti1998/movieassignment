package com.crejo.movieassignment.pojo;

public class Review {

private String movie_name;
private String user_name;
private int rating;
private boolean isCritic = false;
public boolean getisCritic() {
	return isCritic;
}
public void setCritic(boolean isCritic) {
	this.isCritic = isCritic;
}
public Review(String movie_name, String user_name, int rating) {
	this.movie_name = movie_name;
	this.user_name = user_name;
	this.rating = rating;
}
public String getMovie_name() {
	return movie_name;
}
public void setMovie_name(String movie_name) {
	this.movie_name = movie_name;
}
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public int getRating() {
	return rating;
}
public void setRating(int rating) {
	this.rating = rating;
}
@Override
public boolean equals(Object other){
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof Review)) return false;
    Review review = (Review)other;
    if(review.getMovie_name().equals(this.getMovie_name()) && review.getUser_name().equals(this.getUser_name()))
     return true;
     return false;
}

@Override
public int hashCode() {
    return movie_name.hashCode()+user_name.hashCode();
}
	
}
