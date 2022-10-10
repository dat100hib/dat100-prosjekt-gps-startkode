package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

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

		// TODO - START	
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double [] latitudes = new double [gpspoints.length];
		for ( int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
			return latitudes;
		
		
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double [] longitudes = new double [gpspoints.length];
		for ( int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
			return longitudes;
		
		
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;
		
		// TODO - START
		latitude1 = Math.toRadians(gpspoint1.getLatitude());
		latitude2 = Math.toRadians(gpspoint2.getLatitude());
		longitude1 = Math.toRadians(gpspoint1.getLongitude());
		longitude2 = Math.toRadians(gpspoint2.getLongitude());
		
		double deltaLatitude = latitude2 - latitude1;
		double deltaLongitude = longitude2 - longitude1;
		double x = Math.pow(Math.sin(deltaLatitude/2),2); 
		double y = Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(deltaLongitude/2),2);
		double a = x + y;
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		d = R * c;
		
		return d;
		
		
		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;
		
		// TODO - START
		double meter = distance(gpspoint1, gpspoint2);
		
		
		int secs1 = gpspoint1.getTime();
		int secs2 = gpspoint2.getTime();
		secs = secs2 - secs1;
		
		speed = ((meter/secs) * 36)/10;
		
		return speed;
		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		
		// TODO - START
		
		int hr = secs/3600;
		int x = secs%3600;
		int min = x/60;
		int sec = x%60;
		
		/*String hh = String.format("%f", hr);
		String mm = String.format("%f", min);
		String ss = String.format("%f", sec);*/
		
		timestr = String.format("%2s", hr + TIMESEP + min + TIMESEP + sec);
		
		return timestr;
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START

		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}
}
