package com.crejo.movieassignment;

import java.util.ArrayList;

import com.crejo.movieassignment.service.MovieReviewService;
import com.crejo.movieassignment.service.MovieServiceImpls;


public class App {

	public static void main(String[] args) {

		MovieReviewService movieReviewService = new MovieServiceImpls();
		ArrayList<String> genres = new ArrayList<String>();
		genres.add("Comedy");
		genres.add("Action");
		movieReviewService.addMovie("Don", 2006,genres);
		genres = new ArrayList<String>();
		genres.add("Action");
		movieReviewService.addMovie("Padmaavat", 2006,genres);
		genres = new ArrayList<String>();
		genres.add("Drama");
		movieReviewService.addMovie("Tiger", 2008,genres);
		movieReviewService.addMovie("Lunchbox", 2022,genres);
		movieReviewService.addMovie("Guru", 2006,genres);
		genres = new ArrayList<String>();
		genres.add("Romance");
		movieReviewService.addMovie("Metro", 2006,genres);
		
		movieReviewService.addUser("SRK");
		movieReviewService.addUser("Salman");
		movieReviewService.addUser("Deepika");
		
		movieReviewService.addMovieReview("SRK", "Don", 2);
		movieReviewService.addMovieReview("SRK", "Padmaavat", 8);
		movieReviewService.addMovieReview("Salman", "Don", 5);
		movieReviewService.addMovieReview("Deepika", "Don", 9);
		movieReviewService.addMovieReview("Deepika", "Guru", 6);
		movieReviewService.addMovieReview("SRK", "Don", 10);
		movieReviewService.addMovieReview("Deepika", "Lunchbox", 5);
		movieReviewService.addMovieReview("SRK", "Tiger", 5);
		movieReviewService.addMovieReview("SRK", "Metro", 7);


		movieReviewService.getAverageRatingsInGenre("Comedy");
		movieReviewService.getAverageRatingsInYear(2006);
		movieReviewService.getAverageRatingsOfMovie("Don");
		movieReviewService.giveNTopMoviesInGenre("Drama", 1);
		movieReviewService.giveNTopMoviesInYear(2006, 1);
		
	}

}
