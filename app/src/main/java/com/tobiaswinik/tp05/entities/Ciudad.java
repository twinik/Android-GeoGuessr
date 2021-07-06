package com.tobiaswinik.tp05.entities;

public class Ciudad {
    public float lat;
    public float lng;
    public String name;
    public String clase;
    public String countrycode;
    public int population;

    public Ciudad(float lat, float lng, String name, String clase, String countrycode, int population) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.clase = clase;
        this.countrycode = countrycode;
        this.population = population;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}
