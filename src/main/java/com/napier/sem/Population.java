package com.napier.sem;

public class Population {
    // Name of the continent, region, or country
    public String name;

    // Population of people living in cities
    public long populationInCities;

    // Population of people not living in cities
    public long populationNotInCities;

    // Total population
    public long totalPopulation;

    // Constructor for population analysis
    public Population(String name, long populationInCities, long populationNotInCities, long totalPopulation) {
        this.name = name;
        this.populationInCities = populationInCities;
        this.populationNotInCities = populationNotInCities;
        this.totalPopulation = totalPopulation;
    }

    // Constructor for language population
    public Population(String name, long totalPopulation) {
        this.name = name;
        this.totalPopulation = totalPopulation;
    }

}
