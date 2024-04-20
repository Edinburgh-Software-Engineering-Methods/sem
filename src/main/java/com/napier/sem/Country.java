package com.napier.sem;

public class Country {
    // country's code
    public String code;

    //country's name
    public String name;

    // continent of the country
    public String continent;

    // region of the country
    public String region;

    // population of the country
    public int population;

    //capitals of the country
    public String capital;

    public Country(String code, String name, String continent, String region, int population, String capital) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
        this.capital = capital;
    }

    public Country(String name, int population, String capital) {
        this.name = name;
        this.capital = capital;
        this.population = population;
    }
}
