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
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		//copied from ???
		double [] latitude = GPSUtils.getLatitudes(gpspoints);
		double [] longitude = GPSUtils.getLongitudes(gpspoints);
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		setColor (0,255,0);
		int x,y,X,Y;
		
		for (int i=0; i<gpspoints.length-1;i++) {
			
			x = MARGIN + (int) ((longitude[i] - minlon)*xstep());
			y = ybase -(int) ((latitude [i] - minlat)*ystep());
			fillCircle (x,y,2);
			
			X = MARGIN + (int) ((longitude [i+1]- minlon)*xstep());
			Y = ybase -(int) ((latitude [i+1] - minlat)*ystep());
			drawLine (X,Y,x,y);
			
			}
			
			setColor(0,0,255);
		
			int b = fillCircle (MARGIN + (int) 
				((longitude[0] - minlon)*xstep()),ybase -(int) 
				((latitude [0] - minlat)*ystep()),4);

//			int b = fillCircle (58,115,4);
		
			setSpeed(10);
			
		    for (int i=0;i<gpspoints.length;i++) {
				x = MARGIN + (int) ((longitude[i] - minlon)*xstep());
				y = ybase -(int) ((latitude [i] - minlat)*ystep());
				moveCircle (b,x,y);	
				
			}
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(255,0,0);
		setFont("Courier",12);
		
		// TODO - START
		
		int x = 10;
		double WEIGHT = 80.0;

        gpscomputer.displayStatistics(); 
		
		String l = ("====================================");
	    String Time = String.format("Total  Time:      " + GPSUtils.formatTime(gpscomputer.totalTime()));
	    String Distance = String.format("Total distance:" + GPSUtils.formatDouble(gpscomputer.totalDistance()/1000)+ " km");
	    String Elevation = String.format("Total elevation:" +  GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m");
	    String Maxspeed = String.format("Max speed:     " + GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t");
	    String Averagespeed = String.format("Average speed: " + GPSUtils.formatDouble(gpscomputer.averageSpeed()) + " km/t");
	    String Energy = String.format("Energy:         " + GPSUtils.formatDouble(gpscomputer.totalKcal(WEIGHT)) + " kcal");
	    
	    
	    drawString (l, x, TEXTDISTANCE);
		drawString (Time, x, 2* TEXTDISTANCE);
	    drawString (Distance, x, 3* TEXTDISTANCE);
	    drawString (Elevation, x, 4* TEXTDISTANCE);
	    drawString (Maxspeed, x, 5* TEXTDISTANCE);
	    drawString (Averagespeed, x, 6* TEXTDISTANCE);
	    drawString (Energy, x, 7* TEXTDISTANCE);
	    drawString (l, x, 8* TEXTDISTANCE);
	
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT;
	}
 //copied everything from student (574770)
}
