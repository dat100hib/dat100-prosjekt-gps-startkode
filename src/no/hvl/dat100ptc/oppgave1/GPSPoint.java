package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {

	// TODO - objektvariable
<<<<<<< HEAD
	private int time;
	private double latitude;
	private double longitude;
	private double elevation;
=======
	
	private int time;
	private double latitude;
	private double longitude;
	private double elevation;
	
>>>>>>> branch 'master' of https://github.com/669808/dat100-prosjekt-gps-startkode.git
	
	public GPSPoint(int time, double latitude, double longitude, double elevation) {

		// TODO - konstruktør
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
<<<<<<< HEAD
=======
		
		

		// Fjern linjene med throw new etterhvert som metodene implementeres
		//throw new UnsupportedOperationException(TODO.construtor("GPSPoint"));

>>>>>>> branch 'master' of https://github.com/669808/dat100-prosjekt-gps-startkode.git
	}

	// TODO - get/set metoder
	public int getTime() {
<<<<<<< HEAD
		return time;
=======
		
		return time;
				
>>>>>>> branch 'master' of https://github.com/669808/dat100-prosjekt-gps-startkode.git
	}

	public void setTime(int time) {
<<<<<<< HEAD
		this.time = time;
=======
				
		this.time = time;
		//throw new UnsupportedOperationException(TODO.method());

>>>>>>> branch 'master' of https://github.com/669808/dat100-prosjekt-gps-startkode.git
	}

	public double getLatitude() {
<<<<<<< HEAD
		return latitude;
=======
		
		return latitude;
		//throw new UnsupportedOperationException(TODO.method());
		
>>>>>>> branch 'master' of https://github.com/669808/dat100-prosjekt-gps-startkode.git
	}

	public void setLatitude(double latitude) {
<<<<<<< HEAD
		this.latitude = latitude;
=======
		
		this.latitude = latitude;
		
		//throw new UnsupportedOperationException(TODO.method());
		
>>>>>>> branch 'master' of https://github.com/669808/dat100-prosjekt-gps-startkode.git
	}

	public double getLongitude() {
<<<<<<< HEAD
		return longitude;
=======
		
		return longitude;
		
		//throw new UnsupportedOperationException(TODO.method());
		
>>>>>>> branch 'master' of https://github.com/669808/dat100-prosjekt-gps-startkode.git
	}

	public void setLongitude(double longitude) {
<<<<<<< HEAD
		this.longitude = longitude;
=======
		
		this.longitude = longitude;
		//throw new UnsupportedOperationException(TODO.method());
		
>>>>>>> branch 'master' of https://github.com/669808/dat100-prosjekt-gps-startkode.git
	}

	public double getElevation() {
<<<<<<< HEAD
		return elevation;
=======
		
		return elevation;
		
		//throw new UnsupportedOperationException(TODO.method());
		
>>>>>>> branch 'master' of https://github.com/669808/dat100-prosjekt-gps-startkode.git
	}

	public void setElevation(double elevation) {
<<<<<<< HEAD
		this.elevation = elevation;
=======
		
		this.elevation = elevation;
		
		//throw new UnsupportedOperationException(TODO.method());
		
>>>>>>> branch 'master' of https://github.com/669808/dat100-prosjekt-gps-startkode.git
	}
	
	public String toString() {
		
		String str;
		
		// TODO - start
		str=(getTime()+" ("+getLatitude()+","+getLongitude()+") "+getElevation()+"\n");
		return str;
		// TODO - slutt
		
	}
}
