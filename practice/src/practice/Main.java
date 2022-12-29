package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		File file = new File("C:\\Users\\tomhe\\Desktop\\text.txt");
		int valids = 0;

		try {
			Scanner scanner = new Scanner(file);

			while (scanner.hasNext()) {
				String rule = scanner.next();
				char c = scanner.next().charAt(0);
				String password = scanner.next();

				
				int index = rule.indexOf('-');
				int min = Integer.parseInt(rule.substring(0, index));
				int max = Integer.parseInt(rule.substring(index+1, rule.length()));

				min-=1;
				max-=1;
				
				if(password.charAt(min) == c && password.charAt(max) != c) {
					valids++;
				} else if(password.charAt(max) == c && password.charAt(min) != c) {
					valids++;
				}
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int answers = 0;
		
		for(int i = 0; i < 1000; i++) {
			boolean devided = false;
			
			for(int j = 1; j < i; j++) {
				if(i % j == 0) {
					devided = true;
				}
			}
			
			if(devided == true) {
				answers++;
			}
		}
		
		System.out.println(answers);
		
		
	}
}


