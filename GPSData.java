package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {

		// TODO - START

		gpspoints = new GPSPoint[n];
		antall = 0;

		// throw new UnsupportedOperationException(TODO.construtor("GPSData"));

		// TODO - SLUTT
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;

		// TODO - START

		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			inserted = true;
			antall++;
		}

		return inserted;

		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;

		// TODO - START

//		int newtime = Integer.parseInt(time.substring(17,19));
//		int newtime = Integer.parseInt(time.substring(17, 19));
//		double newlatitude = Double.parseDouble(latitude);
//		double newlongitude = Double.parseDouble(longitude);
//		double newelevation = Double.parseDouble(elevation);

		gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);

		boolean inserted = insertGPS(gpspoint);
		return inserted;

		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public void print() {

		System.out.println("====== Konvertert GPS Data - START ======");

		// TODO - START

		for (int i = 0; i < gpspoints.length; i++) {
			System.out.print(gpspoints[i].toString());
			// System.out.print(gpspoints[i]);

		}

		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

		System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
