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
	private static int MAPYSIZE = 550;

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
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat)); 

		return ystep;
		
	}

	public void showRouteMap(int ybase) {

		double[] lats = GPSUtils.getLatitudes(gpspoints);
		double[] longs = GPSUtils.getLongitudes(gpspoints);
		
		double latMin = GPSUtils.findMin(lats);
		double longMin = GPSUtils.findMin(longs);
		
		for (int i = 0; i < lats.length; i++) {

			int y = (int)((lats[i] - latMin)*ystep());
			int x = (int)((longs[i] - longMin)*xstep());

			setColor(30,150,20);
			fillCircle(MARGIN +x , ybase - y, 2);
			
			if (i < lats.length -1)
				drawLine(MARGIN + x, ybase - y, MARGIN + (int)((longs[i+1] - longMin)*xstep()), ybase - (int)((lats[i+1] - latMin)*ystep()));
		}
		
		int xStart = (int)((longs[0] - longMin)*xstep());
		int yStart = (int)((lats[0] - latMin)*ystep());
		setColor(10,10,190);
		int sirkel = fillCircle(MARGIN + xStart, ybase - yStart, 4);
		
		double[] speedTab = new GPSComputer(gpspoints).speeds();
		double maxSpeed = GPSUtils.findMax(speedTab);
		double minSpeed = GPSUtils.findMin(speedTab);
		double SpeedStep = (maxSpeed - minSpeed)/10;
		
		
		for (int i = 0; i < lats.length; i++) {
			int s = 0;
			do {
				if (i < speedTab.length) {
					if ( speedTab[i] < minSpeed + SpeedStep*s)
						setSpeed(s);
					}
				s++;
			}
			while (s < 11);		//fordi setSpeed har verdier mellom 1 og 10
			
			int y = (int)((lats[i] - latMin)*ystep());
			int x = (int)((longs[i] - longMin)*xstep());
			moveCircle(sirkel, MARGIN + x, ybase - y);
		}
		
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Arial",12);
		String[] tab = gpscomputer.displayStatistics();
		int y = TEXTDISTANCE;
		for (int i = 0; i < tab.length; i++) {
			drawString(tab[i], 10, y);
			y+= TEXTDISTANCE;
		}
		
	}

}
