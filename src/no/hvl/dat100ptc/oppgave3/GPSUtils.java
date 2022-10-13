package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import java.math.MathContext;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

        min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
				
			}
		}
				
				return min;

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] latitudes = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude(); 
			
		}
		
		return latitudes;
		
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] longitude = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			longitude[i] = gpspoints[i].getLongitude(); 
			
		}
		
		return longitude;


	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		
		double d;
		double latitude1, longitude1, latitude2, longitude2;

		latitude1 = Math.toRadians(gpspoint1.getLatitude());
		latitude2 = Math.toRadians(gpspoint2.getLatitude());
		longitude1 = Math.toRadians(gpspoint1.getLongitude());
		longitude2 = Math.toRadians(gpspoint2.getLongitude());
		double phi = latitude2 - latitude1;
		double lambda = longitude2 - longitude1;
		
		double a = Math.pow(Math.sin(phi/2),2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(lambda/2),2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		d = R*c;
		return d;

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;
        double distance = distance(gpspoint1, gpspoint2)/1000;
        	
        secs = (gpspoint2.getTime() - gpspoint1.getTime());
        speed = (distance*60*60)/(secs);
        
        return speed;
	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";
		
		int sekunder = secs;

		int sek = secs%60;
		sekunder -= sek;
		int min = (sekunder/60)%60;
		sekunder -= min*60;
		int hr = sekunder/60/60;
		
		String strhr = String.format("%02d", hr);
		String strsek = String.format("%02d", sek);
		String strmin = String.format("%02d", min);
		timestr = "  " + strhr + TIMESEP + strmin + TIMESEP + strsek;
		

		return timestr;
		

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;
		
		String desimalstr = String.format("%.2f", d);
		desimalstr = desimalstr.replace(',' , '.');
		
		str = String.format("%" + TEXTWIDTH + "s", desimalstr);
		
		return str;
		
	}
}
