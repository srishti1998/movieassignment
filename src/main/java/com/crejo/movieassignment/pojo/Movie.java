package com.crejo.movieassignment.pojo;

import java.awt.List;
import java.util.ArrayList;

public class Movie {
private String name;
private int yearOfRelease;
private ArrayList<String> genre;

public Movie(String name, int yearOfRelease, ArrayList<String> genre) {
	this.name = name;
	this.yearOfRelease = yearOfRelease;
	this.genre = genre;

}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getYearOfRelease() {
	return yearOfRelease;
}

public void setYearOfRelease(int yearOfRelease) {
	this.yearOfRelease = yearOfRelease;
}

public ArrayList<String> getGenre() {
	return genre;
}

public void setGenre(ArrayList<String> genre) {
	this.genre = genre;
}


}
