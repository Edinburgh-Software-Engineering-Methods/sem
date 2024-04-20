package com.napier.sem;

public class City {

        // City name
        public String name;

        // Country code of the city
        public String country;

        // District where the city is located
        public String district;

        // Population of the city
        public int population;

        // Constructor with arguments
        public City(String name, String country, String district, int population) {
                this.name = name;
                this.country = country;
                this.district = district;
                this.population = population;
        }
}