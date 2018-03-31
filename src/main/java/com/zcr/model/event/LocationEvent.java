package com.zcr.model.event;

public class LocationEvent extends BaseEvent {
	private String Latitude;
	private String Longitude;
	private String Pricision;

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getPricision() {
		return Pricision;
	}

	public void setPricision(String pricision) {
		Pricision = pricision;
	}

}
