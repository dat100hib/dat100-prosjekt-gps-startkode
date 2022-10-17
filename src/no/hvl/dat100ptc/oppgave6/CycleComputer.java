package no.hvl.dat100ptc.oppgave6;

import javax.swing.JOptionPane;

import easygraphics.*;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.oppgave5.ShowRoute;

public class CycleComputer extends EasyGraphics {

	private static int SPACE = 10;
	private static int MARGIN = 20;
	
	// FIXME: take into account number of measurements / gps points
	private static int ROUTEMAPXSIZE = 800; 
	private static int ROUTEMAPYSIZE = 400;
	private static int HEIGHTSIZE = 200;
	private static int TEXTWIDTH = 200;

	private GPSComputer gpscomp;
	private GPSPoint[] gpspoints;
	
	private int N = 0;

	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;

	public CycleComputer() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");

		gpscomp = new GPSComputer(filename);
		gpspoints = gpscomp.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		N = gpspoints.length; // number of gps points

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));

		xstep = xstep();
		ystep = ystep();

		makeWindow("Cycle Computer", 
				2 * MARGIN + ROUTEMAPXSIZE,
				2 * MARGIN + ROUTEMAPYSIZE + HEIGHTSIZE + SPACE);

		bikeRoute();

	}

	
	public void bikeRoute() {
		
		int x = MARGIN;
		int ybase = 2 * MARGIN + ROUTEMAPYSIZE + HEIGHTSIZE + SPACE;
		
		
		double[] lats = GPSUtils.getLatitudes(gpspoints);
		double[] longs = GPSUtils.getLongitudes(gpspoints);
		
		double latMin = GPSUtils.findMin(lats);
		double longMin = GPSUtils.findMin(longs);

		
		double[] elev = new double[gpspoints.length];

		for (int i = 0; i < elev.length; i++) {
			if (gpspoints[i].getElevation() < 0)
				elev[i] = 0;
			else
				elev[i] = gpspoints[i].getElevation();
			System.out.println(elev[i]);
		}

		int skalering = Integer.parseInt(getText("Skalering: ")); 
		
		double avstand = (maxlat - minlat)*ystep(); //finner avstanden fra høyeste y-punkt i ruten + margin mellom de to grafene
		for (int i = 0; i < lats.length; i++) {

			setColor(3,150,50);
			pause(1/skalering);

			drawLine(x, ybase - MARGIN -  (int)avstand, x, ybase - MARGIN - (int) avstand -(int)elev[i]);
			x += 2;

			int y = (int)((lats[i] - latMin)*ystep());
			int xs = (int)((longs[i] - longMin)*xstep());

			setColor(30,150,20);
			if (i < lats.length -1) {
			if (elev[i] == elev[i+1]) 
				setColor(0,0,255);
			else if (elev[i] < elev[i+1])
				setColor(255,0,0);
			else
				setColor(0,255,0);
				
			fillCircle(MARGIN +xs , ybase - y, 2);
			}
			double[] speed = gpscomp.speeds();
			if (i < lats.length -1) {

				drawLine(MARGIN + xs, ybase - y, MARGIN + (int)((longs[i+1] - longMin)*xstep()), ybase - (int)((lats[i+1] - latMin)*ystep()));
				setColor(255,255,255);
				fillRectangle(0,0,150,50);
				setColor(0,0,0);
				double fart =  ((Math.round(speed[i]))*100)/100.0;
				drawString("Speed:   " + String.valueOf(fart) + " km/t", MARGIN, MARGIN+10);
				setColor(0,0,0);
				String tid = GPSUtils.formatTime(gpspoints[i].getTime());
				drawString(tid, MARGIN, MARGIN);
			}
						
			
		}


	}

	public double xstep() {

		double xstep = ROUTEMAPXSIZE / (Math.abs(maxlon - minlon)); 
		
		return xstep;
	}


	public double ystep() {

		double ystep = ROUTEMAPYSIZE / (Math.abs(maxlat - minlat)); 

		return ystep;
	}

}
