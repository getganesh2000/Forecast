package com.weather.forecast1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast{
	 
    @Override
	public String toString() {
		return "User [latitude=" + latitude + ", longitude=" + longitude + ", timezone=" + timezone + ", summary="
				+ summary + ", icon=" + icon + ", temperature=" + temperature + ", humidity=" + humidity + ", cityName="
				+ cityName + "]";
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	String latitude;
    String longitude;
    String timezone;
    String summary;
    String icon;
    float temperature;
    double humidity;
    String cityName;
    Date timestamp;
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
