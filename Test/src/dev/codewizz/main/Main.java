package dev.codewizz.main;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		List<Integer> list = Arrays.asList(3, 4, 5, 7, 8);
		
		for(Integer i : list) {
			System.out.println(i);
		}
		
		list.add(8);
		
	}
}
