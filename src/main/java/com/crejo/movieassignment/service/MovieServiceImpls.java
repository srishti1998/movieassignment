package com.crejo.movieassignment.service;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.crejo.movieassignment.exceptions.*;
import com.crejo.movieassignment.Util;
import com.crejo.movieassignment.pojo.Movie;
import com.crejo.movieassignment.pojo.Review;
import com.crejo.movieassignment.pojo.User;
import com.crejo.movieassignment.pojo.User_Status;

public class MovieServiceImpls implements MovieReviewService {

	private ArrayList<Movie> movies = new ArrayList<Movie>();
	private ArrayList<User> users = new ArrayList<User>();
	private LinkedHashSet<Review> reviews = new LinkedHashSet<Review>();
	HashMap<String, Integer> movieRatingsPerUser = new HashMap<>();
	HashMap<String, Integer> movieRatingsPerCritic =new HashMap<>();
	private Util util = new Util();

	public List getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}

	public List getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public LinkedHashSet<Review> getReviews() {
		return reviews;
	}

	public void setReviews(LinkedHashSet<Review> reviews) {
		this.reviews = reviews;
	}

	public void addMovie(String name, int yearOfRelease, ArrayList<String> genre) {
		Movie movie = new Movie(name, yearOfRelease, genre);
		movies.add(movie);
	}

	public void addUser(String name) {
		User user = new User(name);
		users.add(user);
	}

	public void addMovieReview(String user_name, String movie_name, int rating) {
		Review review = new Review(movie_name, user_name, rating);
		try {
			if (reviews.contains(review))
				throw new DuplicateReviewException(
						"You cannot rate same movie multiple times Movie Name :" + movie_name + " User: " + user_name);

			Movie movie = getMovieByName(movie_name);
			if (movie.getYearOfRelease() > Year.now().getValue())
				throw new UnreleasedMovieException("Movie not released yet , Movie Name: " + movie_name
						+ " Year of release :" + movie.getYearOfRelease());
			
			reviews.add(review);
			changeUserStatus(user_name);
			if(getUserByName(user_name).getUser_Status().equals(User_Status.CRITIC))
				{review.setRating(rating*2);
				 review.setCritic(true);
				}
		} catch (DuplicateReviewException e) {
			System.out.println(e.getMessage());
		} catch (UnreleasedMovieException e) {
			System.out.println(e.getMessage());
		}

	}

	public Movie getMovieByName(String name) {
		for (Movie movie : movies) {
			if (name.equalsIgnoreCase(movie.getName()))
				return movie;
		}

		return null;
	}

	public void changeUserStatus(String name) {
		for (User user : users) {
			if (user.getName().equalsIgnoreCase(name)) {
				user.setNumber_of_reviews(user.getNumber_of_reviews() + 1);
				if (user.getNumber_of_reviews() > 3)
					user.setUser_Status(User_Status.CRITIC);
				break;
			}
		}

	}

	public User getUserByName(String name) {
		for (User user : users) {
			if (name.equalsIgnoreCase(user.getName()))
				return user;
		}

		return null;
	}

	public void giveNTopMoviesInYear(int year, int n) {

		movieRatingsPerCritic = new HashMap<String, Integer>();
		movieRatingsPerCritic = new HashMap<String, Integer>();
		for (Review review : reviews) {
			Movie movie = getMovieByName(review.getMovie_name());
			User user = getUserByName(review.getUser_name());
			if (movie != null && movie.getYearOfRelease() == year) {
				segregateUserAndCriticRatings(user, review);
			}
		}

		movieRatingsPerUser = util.sortMap(movieRatingsPerUser);
		movieRatingsPerCritic = util.sortMap(movieRatingsPerCritic);

		System.out.println("Top in Year " + year);
		util.displayTopNMovies(movieRatingsPerUser, n);

		System.out.println("Top in Year " + year + " by critics preffered:");
		util.displayTopNMovies(movieRatingsPerCritic, n);

	}

	@Override
	public void giveNTopMoviesInGenre(String genre, int n) {
		movieRatingsPerCritic = new HashMap<String, Integer>();
		movieRatingsPerCritic = new HashMap<String, Integer>();
		for (Review review : reviews) {
			Movie movie = getMovieByName(review.getMovie_name());
			User user = getUserByName(review.getUser_name());
			if (movie != null && movie.getGenre().contains(genre)) {
				segregateUserAndCriticRatings(user, review);
			}
		}

		movieRatingsPerUser = util.sortMap(movieRatingsPerUser);
		movieRatingsPerCritic = util.sortMap(movieRatingsPerCritic);

		System.out.println("Top in Genre " + genre);
		util.displayTopNMovies(movieRatingsPerUser, n);

		System.out.println("Top in Year " + genre + " by critics preffered:");
		util.displayTopNMovies(movieRatingsPerCritic, n);
	}

	@Override
	public void getAverageRatingsInYear(int year) {
		int count = 0;
		double sumOfRatings = 0;
		for (Review review : reviews) {
			Movie movie = getMovieByName(review.getMovie_name());
			User user = getUserByName(review.getUser_name());
			if (movie != null && movie.getYearOfRelease() == year && user != null) {
				sumOfRatings += review.getRating();
				count++;
			}
		}

		System.out.println("Average review score in release year " + year + " :" + sumOfRatings / count);
	}

	@Override
	public void getAverageRatingsInGenre(String genre) {
		int count = 0;
		double sumOfRatings = 0;
		for (Review review : reviews) {
			Movie movie = getMovieByName(review.getMovie_name());
			User user = getUserByName(review.getUser_name());
			if (movie != null && movie.getGenre().contains(genre) && user != null) {
				sumOfRatings += review.getRating();
				count++;
			}
		}

		System.out.println("Average review score in genre " + genre + " :" + sumOfRatings / count);

	}

	@Override
	public void getAverageRatingsOfMovie(String moviename) {
		int count = 0;
		double sumOfRatings = 0;
		for (Review review : reviews) {
			Movie movie = getMovieByName(review.getMovie_name());
			User user = getUserByName(review.getUser_name());
			if (movie != null && movie.getName().equalsIgnoreCase(moviename) && user != null) {
				sumOfRatings += review.getRating();
				count++;
			}
		}

		System.out.println("Average review score of movie " + moviename + " :" + sumOfRatings / count);

	}

	public void segregateUserAndCriticRatings(User user, Review review) {
		if (user != null && !review.getisCritic()) {
			if (movieRatingsPerUser.containsKey(review.getMovie_name()))
				movieRatingsPerUser.put(review.getMovie_name(),
						movieRatingsPerUser.get(review.getMovie_name()) + review.getRating());
			else
				movieRatingsPerUser.put(review.getMovie_name(), review.getRating());
		} else if (user != null && review.getisCritic()) {
			if (movieRatingsPerCritic.containsKey(review.getMovie_name()))
				movieRatingsPerCritic.put(review.getMovie_name(),
						movieRatingsPerUser.get(review.getMovie_name()) + review.getRating());
			else
				movieRatingsPerCritic.put(review.getMovie_name(), review.getRating());

		}
	}

}
