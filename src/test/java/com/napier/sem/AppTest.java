package com.napier.sem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class AppTest {

    private App app;

    @BeforeEach
    public void setUp() {
        // Initialize App instance
        app = new App();

        // connect to the database

        String location = "localhost:33060";
        int delay = 10000;
        app.connect(location,delay);
    }

    @Test
    public void testCountriesInWorld() {
        // Call the method
        app.CountriesInWorld();
    }

    @Test
    public void testCountriesByContinent() {
        // Provide a sample continent
        String continent = "Asia";

        // Call the method
        app.CountriesByContinent(continent);
    }

    @Test
    public void testCountriesByRegion() {
        // Provide a sample region
        String region = "Southeast Asia";

        // Call the method
        app.CountriesByRegion(region);
    }

    @Test
    public void testDisplayCity() {
        // Create sample city list
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City("New York", "United States", "New York", 8000000));
        cities.add(new City("Los Angeles", "United States", "California", 4000000));

        // Call the method
        app.displayCity(cities);

    }

    @Test
    public void testDisplayCapitalCity() {
        // Create sample country list
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("China", 8000000, "Peking"));
        countries.add(new Country("United States", 1000000, "Washington"));

        // Call the method
        app.displayCapitalCity(countries);

    }

    @Test
    public void testDisplayCountry() {
        // Create sample country list
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("USA", "United States", "Asia", "Southeast Asia", 1000000, "Washington"));
        countries.add(new Country("CAN", "Canada", "Europe", "North America", 2000000, "Ottawa"));

        // Call the method
        app.displayCountry(countries);

    }
}