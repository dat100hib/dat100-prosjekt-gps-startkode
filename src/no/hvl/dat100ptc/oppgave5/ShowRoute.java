package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;

	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon));

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {

		double ystep;

		// TODO - START
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		ystep = MAPYSIZE / (Math.abs(maxlat - minlat));

		return ystep;
		// TODO - SLUTT

	}

	public void showRouteMap(int ybase) {

		// TODO - START

		double[] latitudes = GPSUtils.getLatitudes(gpspoints);
		double[] longitudes = GPSUtils.getLongitudes(gpspoints);
		setColor(0, 0, 255);
		for (int i = 0; i < latitudes.length; i++) {
			double a = (latitudes[i] - 60.3) * 10000;
			a = a * 3/4;
			double b = (longitudes[i] - 5.2) * 10000;
			b = b / 2;
			fillCircle((int) b, MAPXSIZE - (int) a, 2);
		}
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0, 0, 0);
		setFont("Courier", 12);

		// TODO - START
		String str = gpscomputer.statisticsStr();
		drawString(str.substring(0,27),MARGIN, MARGIN);
		drawString(str.substring(27,56),MARGIN, MARGIN + TEXTDISTANCE);
		drawString(str.substring(56, 84),MARGIN, MARGIN + 2 * TEXTDISTANCE);
		drawString(str.substring(84,116),MARGIN, MARGIN + 3 * TEXTDISTANCE);
		drawString(str.substring(116,147),MARGIN, MARGIN + 4 * TEXTDISTANCE);
		drawString(str.substring(147, 179),MARGIN, MARGIN + 5 * TEXTDISTANCE);
		
		// TODO - SLUTT;
	}

}
