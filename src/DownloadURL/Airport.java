package DownloadURL;

import java.util.Scanner;

public class Airport {
	
	String code;
	
	public Airport() {
	}
	
	public String getCode() {
		System.out.println("Enter 4-letter ICAO airport code.");
		String input;
		Scanner rl = new Scanner(System.in);
		input = rl.nextLine();
		if (input.length()==4) {
			code = input.toUpperCase();
		} else {
			System.out.println("There is no METAR for this place.");
		}
		rl.close();
		return code;
	}

}
