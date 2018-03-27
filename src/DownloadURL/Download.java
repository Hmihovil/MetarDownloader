package DownloadURL;

import java.io.File;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JProgressBar;



public class Download {

	String link;
	File out;
	
	public Download(String link, File out) {
		this.link = link;
		this.out = out;
		
	}
	
	
	public void start() {
		try {
			URL url = new URL(link);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			double fileSize = (double)http.getContentLengthLong();
			BufferedInputStream in = new BufferedInputStream(http.getInputStream());
			FileOutputStream fos = new FileOutputStream(this.out);
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] buffer = new byte[1024];
			double downloaded = 0.00;
			int read = 0;
			double percentDownloaded = 0.00;
			
			while((read = in.read(buffer, 0, 1024)) >= 0) {
				bout.write(buffer, 0, read);
				downloaded += read;
				percentDownloaded = (downloaded*100)/fileSize;
				
				/* PROGRESS BAR - does not work :( 
				ProgressBar.progress = percentDownloaded;
				if (percentDownloaded<100) {
					ProgressBar.isVisible = true;
				} else if (percentDownloaded ==100) {
					ProgressBar.isVisible = false;
				}
				
				
				if (percentDownloaded<100) {
					ProgressBar.downloadStatus = "Downloading METAR...";
				} else {
					ProgressBar.downloadStatus = "Download complete.";
				}
				*/
				
				String percent = String.format("%.4f", percentDownloaded);
				System.out.println("Downloaded " + percent + "% of file.");
			}
			bout.close();
			in.close();
			System.out.println("Download complete.");
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
