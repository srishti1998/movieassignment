package com.crejo.movieassignment.service;

import java.util.ArrayList;

import com.crejo.movieassignment.pojo.Movie;
import com.crejo.movieassignment.pojo.User;

public interface MovieReviewService {

	public void addMovie(String name, int yearOfRelease, ArrayList<String> genre);
	public void addUser(String name);
	public void addMovieReview(String user_name,String movie_name,int rating);
	public Movie getMovieByName(String name);
	public void changeUserStatus(String name);
	public User getUserByName(String name);
	public void giveNTopMoviesInYear(int year,int n);
	public void giveNTopMoviesInGenre(String genre,int n);
    public void getAverageRatingsInYear(int year);
    public void getAverageRatingsInGenre(String genre);
    public void getAverageRatingsOfMovie(String moviename);

	
}
