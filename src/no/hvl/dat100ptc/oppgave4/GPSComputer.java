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

		// TODO - START
		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}
		return distance;
		// TODO - SLUTT
	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double x = gpspoints[i + 1].getElevation() - gpspoints[i].getElevation();
			if (x >= 0) {
				elevation += x;
			}
		}
		return elevation;
		// TODO - SLUTT
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

		int totalTime = gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();
		return totalTime;
	}

	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {

		// TODO - START // OPPGAVE - START
		double[] gjHastighet = new double[gpspoints.length - 1];
		for (int i = 0; i < gjHastighet.length; i++) {
			gjHastighet[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
		}
		return gjHastighet;
		// TODO - SLUTT

	}

	public double maxSpeed() {

		double maxspeed = 0;

		// TODO - START
		double[] gjHastighet = speeds();
		for (int i = 0; i < gjHastighet.length; i++) {
			if (maxspeed < gjHastighet[i]) {
				maxspeed = gjHastighet[i];
			}
		}
		return maxspeed;
		// TODO - SLUTT

	}

	public double averageSpeed() {

		double average = 0;

		// TODO - START
		average = totalDistance() / totalTime();
		average = average * 3.6;
		return average;
		// TODO - SLUTT
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling, general
	 * 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0 bicycling,
	 * 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9 mph, racing or
	 * leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph, racing/not drafting
	 * or >19 mph drafting, very fast, racing general 12.0 bicycling, >20 mph,
	 * racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	// speed gitt i m/s
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;
		double speedmph = speed * MS;

		// TODO - START
		double s = secs;
		kcal = beregnMET(speedmph) * weight * (s / 3600);

		return kcal;
		// TODO - SLUTT

	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START
		int met = beregnMET(averageSpeed());
		totalkcal = (met * weight * totalTime()) / 3600;
		return totalkcal;
		// TODO - SLUTT

	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START
		String txt1 = String.format("%-15s", "Total time");
		String time = GPSUtils.formatTime(totalTime());
		System.out.println(txt1 + ":" + time + " ");

		String txt2 = String.format("%-15s", "Total Distance");
		String distance = GPSUtils.formatDouble(totalDistance());
		System.out.println(txt2 + ":" + distance + " km");

		String txt3 = String.format("%-15s", "Total Elevation");
		String elevation = GPSUtils.formatDouble(totalElevation());
		System.out.println(txt3 + ":" + elevation + " m");

		String txt4 = String.format("%-15s", "Max Speed");
		String maxSpeed = GPSUtils.formatDouble(maxSpeed());
		System.out.println(txt4 + ":" + maxSpeed + " km/t");

		String txt5 = String.format("%-15s", "Average Speed");
		String average = GPSUtils.formatDouble(averageSpeed());
		System.out.println(txt5 + ":" + average + " km/t");

		String txt6 = String.format("%-15s", "Energy");
		String energy = GPSUtils.formatDouble(totalKcal(WEIGHT));
		System.out.println(txt6 + ":" + energy + " kcal");

		// TODO - SLUTT

		System.out.println("==============================================");

	}

	// Egen metode for å beregne MET, hastighet i mph
	public int beregnMET(double speedmph) {

		int met = 0;

		if (speedmph < 10) {
			met = 4;
		} else if (speedmph < 12) {
			met = 6;
		} else if (speedmph < 14) {
			met = 8;
		} else if (speedmph < 16) {
			met = 10;
		} else if (speedmph < 20) {
			met = 12;
		} else if (speedmph > 20) {
			met = 16;
		}
		return met;
	}

	
	//Egen metode for bruk i ShowRoute
	public String statisticsStr() {
		
		String txt1 = String.format("%-15s", "Total time");
		String time = GPSUtils.formatTime(totalTime());
		txt1 = (txt1 + ":" + time + " ");

		String txt2 = String.format("%-15s", "Total Distance");
		String distance = GPSUtils.formatDouble(totalDistance());
		txt2 = (txt2 + ":" + distance + " km");

		String txt3 = String.format("%-15s", "Total Elevation");
		String elevation = GPSUtils.formatDouble(totalElevation());
		txt3 = (txt3 + ":" + elevation + " m");

		String txt4 = String.format("%-15s", "Max Speed");
		String maxSpeed = GPSUtils.formatDouble(maxSpeed());
		txt4 = (txt4 + ":" + maxSpeed + " km/t");

		String txt5 = String.format("%-15s", "Average Speed");
		String average = GPSUtils.formatDouble(averageSpeed());
		txt5 = (txt5 + ":" + average + " km/t");

		String txt6 = String.format("%-15s", "Energy");
		String energy = GPSUtils.formatDouble(totalKcal(WEIGHT));
		txt6 = (txt6 + ":" + energy + " kcal");

		String str = (txt1 + txt2 + txt3 + txt4 + " " + txt5 + txt6 + " ");

		return str;
	}
}
