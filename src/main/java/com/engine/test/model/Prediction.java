package com.engine.test.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Prediction {
	private long id;
	private String lineId;
	private String lineName;
	private String platformName;
	private String direction;
	private String bearing;
	private String destinationNaptanId;
	private String destinationName;
	private int timeToStation;
	private String currentLocation;
	private String towards;
	private String expectedArrival;
	private String timeToLive;
	private String modeName;
	
	private String printTimeToStation;
	private String printEstimatedArrival;
	
	public String getModeName() {
		return modeName;
	}
	public void setModeName(String modeName) {
		this.modeName = modeName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getBearing() {
		return bearing;
	}
	public void setBearing(String bearing) {
		this.bearing = bearing;
	}
	public String getDestinationNaptanId() {
		return destinationNaptanId;
	}
	public void setDestinationNaptanId(String destinationNaptanId) {
		this.destinationNaptanId = destinationNaptanId;
	}
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public int getTimeToStation() {
		return timeToStation;
	}
	public void setTimeToStation(int timeToStation) {
		this.timeToStation = timeToStation;
	}
	public String getTowards() {
		return towards;
	}
	public void setTowards(String towards) {
		this.towards = towards;
	}
	public String getExpectedArrival() {
		return expectedArrival;
	}
	public void setExpectedArrival(String expectedArrival) {
		this.expectedArrival = expectedArrival;
	}
	public String getTimeToLive() {
		return timeToLive;
	}
	public void setTimeToLive(String timeToLive) {
		this.timeToLive = timeToLive;
	}
	
	/*
	 * User friendly format for arrival time
	 */
	public String getPrintTimeToStation() {
		int mins = (int)(Math.floor(this.timeToStation)/60);
		this.printTimeToStation = mins==0? "Due " : mins + " mins";
		return this.printTimeToStation;
	}
		
	/*
	 * User friendly format to know estimated arrival date and time
	 */
	public String getPrintEstimatedArrival() {
		try {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
			//REST API returns time such as 2019-09-16T09:30:12Z
			LocalDateTime expectedTime = LocalDateTime.parse(getExpectedArrival(), format);
			//First convert the UTC time to London time string
			ZonedDateTime londonZonedDateTime = (expectedTime.atZone(ZoneId.of("UTC"))).withZoneSameInstant(ZoneId.of("Europe/London"));
			expectedTime = LocalDateTime.parse(format.format(londonZonedDateTime), format);
			
		    LocalDateTime current = LocalDateTime.now();
		    long duration = (Duration.between(current,expectedTime).getSeconds())/3600;
		    if(duration<24 && (expectedTime.getDayOfMonth()==current.getDayOfMonth())) 
		    	this.printEstimatedArrival = expectedTime.format(DateTimeFormatter.ofPattern("HH:mm a"));
		    else
		    	this.printEstimatedArrival = expectedTime.format(DateTimeFormatter.ofPattern("dd MMM HH:mm a"));
		} catch (Exception e) {
			this.printEstimatedArrival = "No data";
		}
		
		return this.printEstimatedArrival;
	}
	
	public void setPrintTimeToStation(String printTimeToStation) {
		this.printTimeToStation = printTimeToStation;
	}
	public void setPrintEstimatedArrival(String printEstimatedArrival) {
		this.printEstimatedArrival = printEstimatedArrival;
	}
		
}
