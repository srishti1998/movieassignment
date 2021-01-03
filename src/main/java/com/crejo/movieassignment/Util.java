package com.crejo.movieassignment;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Util {
	public HashMap<String, Integer> sortMap(HashMap<String, Integer> map) {
		HashMap<String, Integer> sortedMap = new HashMap<String, Integer>();
		map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> {
			sortedMap.put(x.getKey(), x.getValue());
		});
		return sortedMap;
	}
	
	public void displayTopNMovies(HashMap<String, Integer> map, int n)
	{
		for(Map.Entry<String, Integer> entry:map.entrySet())
		{
		 System.out.println(entry.getKey() +" with ratings: "+ entry.getValue());
		 n--;
		 if(n==0)
			 break;
		}
		
	}
	

}
