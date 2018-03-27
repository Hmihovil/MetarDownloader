package MetarWindow;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import DownloadURL.Airport;
import DownloadURL.Download;
import DownloadURL.ProgressBar;
import DownloadURL.ReadFile;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.plaf.synth.SynthButtonUI;

import DecodeMetar.Decoder;

import javax.swing.JProgressBar;
import java.awt.Color;



import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MetarApp {
	
	 static final int MY_MINIMUM = 0;

	  static final int MY_MAXIMUM = 100;

	private JFrame frame;
	private JTextField textField;
	
	
	public static String airportCode;
	public static String print;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MetarApp app = new MetarApp();
					app.frame.setVisible(true);
					String link = "https://aviationweather.gov/adds/dataserver_current/current/metars.cache.csv";
					File out = new File("src/metar.txt");
					Download download = new Download(link, out);
					download.start();
					Decoder decoder = new Decoder();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application.
	 */
	public MetarApp() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) {
		            e.printStackTrace();
		 }
		 
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 700, 490);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		
		
		try {
	          frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/appBack4.jpg")))));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        frame.pack();
	        frame.setVisible(true);
		
		
		File in = new File("src/metar.txt");
		ReadFile read = new ReadFile(in);
		Decoder decoder = new Decoder();
		
		//create button and field objects
		JTextArea textArea = new JTextArea();
		textField = new JTextField();
		JButton btnShow = new JButton("METAR");
		JRadioButton rdbtnDecode = new JRadioButton("");
		JButton btnRefresh = new JButton("Refresh");
		
		
		frame.getContentPane().add(textArea);
		frame.getContentPane().add(btnShow);
		frame.getContentPane().add(textField);
		


		
		textArea.setForeground(Color.WHITE);
		textArea.setBounds(46, 109, 612, 292);
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textArea.setBackground(new Color(128, 128, 128));
		textArea.setOpaque(false);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		
		
		textField.setForeground(new Color(255, 255, 255));
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textField.setBounds(520, 28, 52, 38);
		textField.setColumns(10);
		textField.setOpaque(false);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				
				airportCode = textField.getText().toUpperCase();

				rdbtnDecode.setSelected(false);
				String allLine = decoder.saveAllLine(read, airportCode);
				String raw = decoder.showRawMetar(allLine);
				print = raw;
				textArea.setText(print);
			}
		});
		
		
		btnShow.setForeground(new Color(255, 255, 255));
		btnShow.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		btnShow.setBounds(581, 28, 93, 38);
		//make button transparent
		//btnShow.setUI(new MetalButtonUI());
		btnShow.setBackground(new Color(255,255,255));
		btnShow.setOpaque(false);
		frame.getContentPane().setLayout(null);
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				airportCode = textField.getText().toUpperCase();
				rdbtnDecode.setSelected(false);
				String allLine = decoder.saveAllLine(read, airportCode);
				String raw = decoder.showRawMetar(allLine);
				print = raw;
				textArea.setText(print);
			}
		});
		
		
		
		
		rdbtnDecode.setFont(new Font("Palatino Linotype", Font.PLAIN, 16));
		rdbtnDecode.setForeground(Color.WHITE);
		rdbtnDecode.setBounds(59, 432, 153, 23);
		rdbtnDecode.setBackground(new Color(255,255,255));
		rdbtnDecode.setOpaque(false);
		frame.getContentPane().add(rdbtnDecode);
		rdbtnDecode.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				
				airportCode = textField.getText().toUpperCase();
				String allLine = decoder.saveAllLine(read, airportCode);
				String raw = decoder.showRawMetar(allLine);
				String decoded = decoder.showDecoded(allLine);
				print = raw;
				
				if(rdbtnDecode.isSelected()) {
					textArea.setText(decoded);
				}
				if(rdbtnDecode.isSelected() == false) {
					textArea.setText(raw);
				}
				
			}
		});
		
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.setFont(new Font("Palatino Linotype", Font.PLAIN, 16));
		btnRefresh.setBounds(581, 417, 93, 38);
		//make button transparent
		//btnRefresh.setUI(new MetalButtonUI());
		btnRefresh.setBackground(new Color(255, 255, 255));
		btnRefresh.setOpaque(false);
		frame.getContentPane().add(btnRefresh);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				String link = "https://aviationweather.gov/adds/dataserver_current/current/metars.cache.csv";
				File out = new File("src/metar.csv");
				Download download = new Download(link, out);
				download.start();
			}
		});
		

		
		/* PROGRESS BAR - does not work :(
		JProgressBar progBar = new JProgressBar();
		frame.getContentPane().add(progBar);
		progBar.setBounds(293, 435, 146, 20);
		progBar.setMinimum(MY_MINIMUM);
		progBar.setMaximum(MY_MAXIMUM);
		progBar.setBackground(new Color(200,200,200));
		progBar.setForeground(new Color(100,100,100));
		progBar.setVisible(ProgressBar.isVisible);
		
		
		
	    
		for (int i = MY_MINIMUM; i <= MY_MAXIMUM; i++) {
		      try {
		        SwingUtilities.invokeLater(new Runnable() {
		          public void run() {
		        	  progBar.setValue((int)ProgressBar.progress);
		              progBar.update(progBar.getGraphics());
		          }
		        });
		        java.lang.Thread.sleep(100);
		      } catch (InterruptedException e) {
		        
		      }
		}
		*/
		
	}
}
