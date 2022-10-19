package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
		}
		return distance;

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		for (int i = 0; i < gpspoints.length - 1; i++) {
			if (gpspoints[i+1].getElevation() > gpspoints[i].getElevation()) {
				elevation += gpspoints[i+1].getElevation() - gpspoints[i].getElevation();
			}
		}
		return elevation;

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		int lengde = gpspoints.length - 1;
		
		int time = gpspoints[lengde].getTime() - gpspoints[0].getTime();
		
		return time;
	}
		

	public double[] speeds() {
		
		double [] speedTab = new double [gpspoints.length - 1];
		for (int i = 0; i<gpspoints.length-1; i++)  {
			speedTab[i] = GPSUtils.speed(gpspoints[i], gpspoints [i+1]);
		}
		
		return speedTab;
		

	}
	
	public double maxSpeed() {
		
		
		double [] maxSpeedTab = speeds();
		double maxspeed = GPSUtils.findMax(maxSpeedTab);
		
		
		return maxspeed;
		
		
	}

	public double averageSpeed() {

		double average;
		
		average = ((totalDistance()/totalTime())*60*60)/1000;
		return average;
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;
		
		if (speedmph < 10) 
			met = 4.0;
		else if (speedmph < 12) 
			met = 6.0;
		else if (speedmph < 14)
			met = 8.0;
		else if (speedmph < 16)
			met = 10.0;
		else if (speedmph < 20)
			met = 12.0;
		else
			met = 16.0;
		kcal = (met * weight * secs)/60/60;
		
		return kcal;
		
	}

	public double totalKcal(double weight) {
		
		double totalkcal = 0;
		double[] speed = speeds();
		int[] time = new int[speed.length];
		for (int i = 0; i < time.length; i++) {
			time[i] = gpspoints[i+1].getTime() - gpspoints[i].getTime(); 
			totalkcal += kcal(weight, time[i], speed[i]);
		}

		return totalkcal;
	}
	
	private static double WEIGHT = 80.0;
	
	public String[] displayStatistics() {

		System.out.println("==============================================");
		int TEXTWIDTH = 15;
		String er = "==============================================";
		String tid = (GPSUtils.formatTime(totalTime()));
		double distanse = Math.round(totalDistance()*100)/100.0;
		double totalElev = Math.round(totalElevation()*100)/100.0;
		double maxSpeed = Math.round(maxSpeed()*100)/100.0;
		double average = Math.round(averageSpeed()*100)/100.0;
		double energi = Math.round(totalKcal(WEIGHT)*100)/100;
		
		String totTime = (String.format("%" + -TEXTWIDTH + "s", "Total Time") + ":" + String.format("%" + 12 + "s", tid));
		String totDist = (String.format("%" + -TEXTWIDTH + "s", "Total distance") + ":" + String.format("%" + 12 + "s", String.format("%.2f", distanse)) + "   km");
		String totEle = (String.format("%" + -TEXTWIDTH + "s", "Total elevation") + ":" + String.format("%" + 12 + "s", String.format("%.2f", totalElev)) + "    m");
		String maxS = (String.format("%" + -TEXTWIDTH + "s", "Max speed") + ":" + String.format("%" + 12 + "s", String.format("%.2f", maxSpeed)) + " km/t");
		String avgS = (String.format("%" + -TEXTWIDTH + "s", "Average speed") + ":" + String.format("%" + 12 + "s", String.format("%.2f", average))  + " km/t");
		String Ener = (String.format("%" + -TEXTWIDTH + "s", "Energy") + ":" + String.format("%" + 12 + "s", String.format("%.2f", energi)) + " kcal");
		
		totDist = totDist.replace(',' , '.');
		totEle = totEle.replace(',', '.');
		maxS = maxS.replace(',', '.');
		avgS = avgS.replace(',', '.');
		Ener = Ener.replace(',', '.');
		
		System.out.println(totTime);
		System.out.println(totDist);
		System.out.println(totEle);
		System.out.println(maxS);
		System.out.println(avgS);
		System.out.println(Ener);
		
		String[] strTab = new String[6];
		strTab[0] = totTime;
		strTab[1] = totDist;
		strTab[2] = totEle;
		strTab[3] = maxS;
		strTab[4] = avgS;
		strTab[5] = Ener;
		
		System.out.println("==============================================");
		
		return strTab;
	}
	
	public double[] climbs() {
		//beregner stigningsprosenten
		//elevation / distance x 100
		//må opprette en tabell for de ulike stigningsprosentene
		double[] stigning = new double[gpspoints.length - 1];
		for (int i = 0; i < stigning.length; i++) {
			double elev = (gpspoints[i + 1].getElevation() - gpspoints[i].getElevation());
			double dist = GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
			double rad = Math.toRadians(elev/dist);
			stigning[i] = Math.round((Math.tan(rad))*100*100)/100.0;
			
		}

		return stigning;
		
		
	}
	
	public double maxClimb() {
		double[] stigning = climbs();
		double max = GPSUtils.findMax(stigning);
		
		return max;
	}

}
