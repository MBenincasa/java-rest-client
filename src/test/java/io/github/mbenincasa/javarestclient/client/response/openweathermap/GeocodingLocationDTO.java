package io.github.mbenincasa.javarestclient.client.response.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeocodingLocationDTO {

    private String name;
    @JsonProperty("local_names")
    private LocalNames localNames;
    private Double lat;
    private Double lon;
    private String country;
    private String state;

    public GeocodingLocationDTO() {
    }

    public GeocodingLocationDTO(String name, LocalNames localNames, Double lat, Double lon, String country, String state) {
        this.name = name;
        this.localNames = localNames;
        this.lat = lat;
        this.lon = lon;
        this.country = country;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalNames getLocalNames() {
        return localNames;
    }

    public void setLocalNames(LocalNames localNames) {
        this.localNames = localNames;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "GeocodingLocationDTO{" +
                "name='" + name + '\'' +
                ", localNames=" + localNames +
                ", lat=" + lat +
                ", lon=" + lon +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
