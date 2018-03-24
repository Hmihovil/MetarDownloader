package DecodeMetar;

import DownloadURL.ReadFile;

public class Decoder {
	
	
	public void Decoder() {
	}
	
	public void decode(String raw) {
		int i = 0;
		while (i < raw.length()) {
			
			i++;
		}
		
	}
	
	//save all line of text, which begins with String airport code XXXX
	public String saveAllLine(ReadFile read, String airportCode) {
		String allLine = read.findAirport(airportCode);
		return allLine;
	}
	
	//save first part of text - raw metar
	public String showRawMetar(String allLine) {
		String rawMetar = allLine.substring(0, allLine.indexOf(","));
		return rawMetar;
	}
	

	
	public String showDecoded(String allLine) {
		String decoded = "";
		String[] s = allLine.split(",");
		String[] p = new String[31];
		
		
		//BASIC INFO
		
		String station_id = "Airport: " + s[1];
		String observation_time = s[2];
		String airport_date_time = "Airport: " + s[1] + "\t     Date: " + s[2].substring(0, 10) + "      Zulu time: " + s[2].substring(11, 20) +"\n";
		p[0] = airport_date_time;
		
		String latitude = s[3];
		String longitude = s[4];
		
		String temp_c = "\nTemperature:\t           " + s[5] + " C";
		p[1] = temp_c;
		
		String dewpoint_c = "\nDewpoint:\t           " + s[6] + " C";
		p[2] = dewpoint_c;
		
		//WIND INFO
		
		String wind_dir_degrees = s[7] + " degrees, ";
		if (s[7].equals("0")) {
			wind_dir_degrees = "variable at ";
		}
		String wind_speed_kt = s[8] + " knots";
		String wind_gust_kt = ", gusting " + s[9] + " knots";
		if (s[9].equals("")) {
			wind_gust_kt = "";
		}
		String wind = "\nWind:\t           " + wind_dir_degrees + wind_speed_kt + wind_gust_kt;
		p[3] = wind;
		
		//VISIBILITY AND PRESSURE
		
		String visibility_statute_mi = "\nVisibility:\t           " + s[10] + " statue miles";
		p[4] = visibility_statute_mi;
		
		String altim_in_hg = "\nAltimeter:\t           " + s[11].substring(0, 5) + " mmHg";
		p[5] = altim_in_hg;
		
		String sea_level_pressure_mb = "\nPressure at sea level:  " + s[12] + " mb";
		if (s[12].equals("")) {
			sea_level_pressure_mb = "";
		}
		p[6] = sea_level_pressure_mb;
		
		String corrected = s[13];
		if (s[13].equals("TRUE")) {
			corrected = "\nCorrected";
		}
		p[7] = corrected;
		
		//INDICATORS AND SENSORS
		
		String auto = s[14];
		if (s[14].equals("TRUE")) {
			auto = "\nAuto";
		}
		p[24] = auto;
		
		String auto_station = s[15];
		if (s[15].equals("TRUE")) {
			auto_station = "\nAuto station";
		}
		p[25] = auto_station;
		
		String maintenance_indicator_on = s[16];
		if (s[16].equals("TRUE")) {
			maintenance_indicator_on = "\nMaintenance indicator on";
		}
		p[26] = maintenance_indicator_on;
		
		String no_signal = s[17];
		if (s[17].equals("TRUE")) {
			no_signal = "\nNo signal";
		}
		p[27] = no_signal;
		
		String lightning_sensor_off = s[18];
		if (s[18].equals("TRUE")) {
			lightning_sensor_off = "\nLightning sensor off";
		}
		p[28] = lightning_sensor_off;
		
		String freezing_rain_sensor_off = s[19];
		if (s[19].equals("TRUE")) {
			freezing_rain_sensor_off = "\nFreezing rain sensor off";
		}
		p[29] = freezing_rain_sensor_off;
		
		String present_weather_sensor_off = s[20];
		if (s[20].equals("TRUE")) {
			present_weather_sensor_off = "\nPresent weather sensor off";
		}
		p[30] = present_weather_sensor_off;
		
		
		
		//WEATHER STRING
		
		String wx_string = checkWx(s[21]);
		wx_string = "\nRemarks:\t           " + wx_string;
		if (s[21].equals("")) {
			wx_string = "";
		}
		p[8] = wx_string;
		
		
		
		//CLOUDS AND SKY COVERAGE
		
		String sky_cover = checkClouds(s[22]);
		String cloud_base_ft_agl = s[23] + " ft ";
		if (s[23].equals("")) {
			cloud_base_ft_agl = "";
		}
		
		String sky_cover1 = checkClouds(s[24]);
		String cloud_base_ft_agl1 = s[25] + " ft ";
		if (s[25].equals("")) {
			cloud_base_ft_agl1 = "";
		}
		
		String sky_cover2 = checkClouds(s[26]);
		String cloud_base_ft_agl2 = s[27] + " ft ";
		if (s[27].equals("")) {
			cloud_base_ft_agl2 = "";
		}
		
		String sky_cover3 = checkClouds(s[28]);
		String cloud_base_ft_agl3 = s[29] + " ft ";
		if (s[29].equals("")) {
			cloud_base_ft_agl3 = "";
		}
		
		String clouds = "\nSky coverage AGL:       " + sky_cover + cloud_base_ft_agl + sky_cover1 + cloud_base_ft_agl1 + sky_cover2 
				+ cloud_base_ft_agl2 + sky_cover3 + cloud_base_ft_agl3;
		p[9] = clouds;
		
		String flight_category = "\nFlight category:           " + s[30];
		p[10] = flight_category;
		
		String three_hr_pressure_tendency_mb = "\n3hr pressure tend.:    " + s[31] + " mb";
		if (s[31].equals("")) {
			three_hr_pressure_tendency_mb = "";
		}
		p[11] = three_hr_pressure_tendency_mb;
		
		String maxT_c = "\nMax temperature:        " + s[32] + " C";
		if (s[32].equals("")) {
			maxT_c = "";
		}
		p[12] = maxT_c;
	
		String minT_c = "\nMin temperature:        " + s[33] + " C";
		if (s[33].equals("")) {
			minT_c = "";
		}
		p[13] = minT_c;

		String maxT24hr_c = "\nMax temp in 24hr:       " + s[34] + " C";
		if (s[34].equals("")) {
			maxT24hr_c = "";
		}
		p[14] = maxT24hr_c;

		String minT24hr_c = "\nMin temp in 24hr:       " + s[35] + " C";
		if (s[35].equals("")) {
			minT24hr_c = "";
		}
		p[15] = minT24hr_c;
		
		String precip_in = "\nPrecipitation:\t           " + s[36] + " in";
		if (s[36].equals("")) {
			precip_in = "";
		}
		p[16] = precip_in;
		
		String pcp3hr_in = "\nPrecip. in 3hr:\t           " + s[37] + " in";
		if (s[37].equals("")) {
			pcp3hr_in = "";
		}
		p[17] = pcp3hr_in;
		
		String pcp6hr_in = "\nPrecip. in 6hr:\t           " + s[38] + " in";
		if (s[38].equals("")) {
			pcp6hr_in = "";
		}
		p[18] = pcp6hr_in;
		
		String pcp24hr_in = "\nPrecip. in 24hr:\t           " + s[39] + " in";
		if (s[39].equals("")) {
			pcp24hr_in = "";
		}
		p[19] = pcp24hr_in;
		
		String snow_in = "\nSnow:\t           " + s[40] + " in";
		if (s[40].equals("")) {
			snow_in = "";
		}
		p[20] = snow_in;
		
		String vert_vis_ft = "\nVertical visibility:    " + s[41] + " ft";
		if (s[41].equals("")) {
			vert_vis_ft = "";
		}
		p[21] = vert_vis_ft;
		
		String metar_type = "\nMetar type:\t           " + s[42];
		if (s[42].equals("")) {
			metar_type = "";
		}
		p[22] = metar_type;
		
		String elevation_m = "\nElevation:\t           " + s[43] + " m";
		if (s[43].equals("")) {
			elevation_m = "";
		}
		p[23] = elevation_m;
		
		
	
		for (int i = 0; i < p.length; i++) {
			decoded+= p[i];
		}
		
		return decoded;
	}

	
	//SKY COVERAGE
	private String checkClouds(String s) {
		String sky_cover;
		switch (s) {
		case "FEW":
		    sky_cover = "few at ";
		    break;
		case "SCT":
		    sky_cover = "scattered at ";
		    break;
		case "BKN":
			sky_cover = "broken at ";
			break;
		case "OVC":
			sky_cover = "overcast at ";
			break;
		case "CLR":
			sky_cover = "clear";
			break;
		case "CAVOK":
			sky_cover = "ceiling and visibility OK";
			break;
		case "TCU":
			sky_cover = "towering cumulus at ";
			break;
		case "CB":
			sky_cover = "cumulonimbus at ";
			break;
		case "BCFG":
			sky_cover = "moderate fog patches, ";
			break;
		default: 
		    sky_cover = s;
		    break;
		}
		return sky_cover;
	}
	
	//WEATHER REMARKS
	private String checkWx(String s) {
		String wx = s;

		
		//INTENSITY
		if (s.equals("")) {
			wx = "";
		} else if ((s.substring(0, 1)).equals("-")) {
			wx = "light ";
		} else if ((s.substring(0, 1)).equals("+")) {
			wx = "heavy ";
		} else if ((s.substring(0, 2)).equals("VC")) {
			wx = "vicinity ";
		} else {
			wx = "moderate ";
		}
		
		//DESCRIPTION
		String[] desc = {"MI", "PR", "BC", "DR", "BL", "SH", "TS", "FZ"};
		for (int i = 0; i<desc.length; i++) {
			if (s.contains(desc[i])) {
				switch(desc[i]) {
				case "MI":
					wx += "shallow ";
					break;
				case "PR":
					wx += "partial ";
					break;
				case "BC":
					wx += "patches ";
					break;
				case "DR":
					wx += "low drifting ";
					break;
				case "BL":
					wx += "blowing ";
					break;
				case "SH":
					wx += "showers ";
					break;
				case "TS":
					wx += "thunderstorm ";
					break;
				case "FZ":
					wx += "freezing ";
					break;
				}
			}
		}
		
		//PRECIPITATION AND OBSCURATION
		String[] prec = {"DZ", "RA", "SN", "SG", "IC", "PL", "GR", "GS", "UP", "BR", "FG", "FU", "VA", "DU", "SA", "HZ", "PY",
				"PO", "SQ", "FC", "SS", "DS"};
		for (int i = 0; i<prec.length; i++) {
			if (s.contains(prec[i])) {
				switch(prec[i]) {
				case "DZ":
					wx += "drizzle";
					break;
				case "RA":
					wx += "rain";
					break;
				case "SN":
					wx += "snow";
					break;
				case "SG":
					wx += "snow grains";
					break;
				case "IC":
					wx += "ice crystals";
					break;
				case "PL":
					wx += "ice pellets";
					break;
				case "GR":
					wx += "hail";
					break;
				case "GS":
					wx += "small hail";
					break;
				case "UP":
					wx += "unknown";
					break;
				case "BR":
					wx += "mist ";
					break;
				case "FG":
					wx += "fog ";
					break;
				case "FU":
					wx += "smoke ";
					break;
				case "VA":
					wx += "volcanic ash ";
					break;
				case "DU":
					wx += "widespread dust haze ";
					break;
				case "SA":
					wx += "sand ";
					break;
				case "HZ":
					wx += "haze ";
					break;
				case "PY":
					wx += "spray ";
					break;
				case "PO":
					wx += "well developed dust / sand whirls ";
					break;
				case "SQ":
					wx += "squalls ";
					break;
				case "FC":
					wx += "funnel clouds, inc tornadoes / waterspouts ";
					break;
				case "SS":
					wx += "sandstorm ";
					break;
				case "DS":
					wx += "duststorm ";
					break;
				}
			}
		}
		
		return wx;
	}
	
	
}
