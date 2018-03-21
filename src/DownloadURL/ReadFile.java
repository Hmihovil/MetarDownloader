package DownloadURL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
	
	File in;
	String airport;
	String metarText;
	
	public ReadFile (File in) {
		this.in = in;
		}
	
	public void findAirport(String airport) {
		try {
			Scanner rf = new Scanner(in, "UTF-8");
			
			while (rf.hasNextLine()) {
				String line = rf.nextLine();
				if (line.substring(0,4).equals(airport)) {
					metarText = line;
					System.out.println(metarText);
				} 
			}
			rf.close();
		} catch (FileNotFoundException e) {
			System.out.println("Brak pliku w podanej ścieżce.");
		}
}
}


