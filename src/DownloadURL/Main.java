package DownloadURL;

import java.io.File;

public class Main {

	public static void main(String[] args) {

		String link = "https://aviationweather.gov/adds/dataserver_current/current/metars.cache.csv";
		File out = new File("src/metar.csv");
		new Thread(new Download(link, out)).start();
		ReadFile read = new ReadFile(out);
		Airport airport = new Airport();
		read.findAirport(airport.getCode());
		
	}

}
