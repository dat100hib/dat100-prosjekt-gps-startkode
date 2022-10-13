package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt
	// dvs. ignorer information om dato og omregn tidspunkt til sekunder
	// Eksempel - tidsinformasjon (som String): 2017-08-13T08:52:26.000Z
    // skal omregnes til sekunder (som int): 8 * 60 * 60 + 52 * 60 + 26 
	
	private static int TIME_STARTINDEX = 11; // posisjon for start av tidspunkt i timestr

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;
		
		String regex = "(-|T|Z|:)+";
		String [] tid = timestr.split(regex);

		if (tid[3].substring(0,1) == "0") 
			hr = Integer.parseInt((tid[3].substring(1,2)))*60*60; 
		else
			hr = Integer.parseInt(tid[3])*60*60;

		System.out.println(hr);
		if (tid[4].substring(0,1) == "0")
			min = Integer.parseInt((tid[4].substring(1,2)))*60;
		else
			min = Integer.parseInt(tid[4])*60;
		System.out.println(min);
		double sek;
		if (tid[5].substring(0,1) == "0")
			sek = Double.parseDouble((tid[5].substring(1,2)));
		else 
			sek = Double.parseDouble(tid[5]);
		System.out.println(sek);
		sec = (int)sek;
		secs = hr + min + sec;
		
		return secs;
		
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;
		
		gpspoint = new GPSPoint(toSeconds(timeStr), Double.parseDouble(latitudeStr), Double.parseDouble(longitudeStr), Double.parseDouble(elevationStr));
	    
		return gpspoint;
	}
	
}
