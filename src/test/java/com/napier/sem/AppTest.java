package com.napier.sem;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class AppTest {

    @Test
    public void testDisplayCity() {
        // Create sample city list
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City("New York", "United States", "New York", 8000000));
        cities.add(new City("Los Angeles", "United States", "California", 4000000));

        // Call the method
        App app = new App();
        app.displayCity(cities);

    }

    @Test
    public void testDisplayCapitalCity() {
        // Create sample country list
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("China", 8000000, "Peking"));
        countries.add(new Country("United States", 1000000, "Washington"));

        // Call the method
        App app = new App();
        app.displayCapitalCity(countries);

    }

    @Test
    public void testDisplayCountry() {
        // Create sample country list
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("USA", "United States", "Asia", "Southeast Asia", 1000000, "Washington"));
        countries.add(new Country("CAN", "Canada", "Europe", "North America", 2000000, "Ottawa"));

        // Call the method
        App app = new App();
        app.displayCountry(countries);

    }
}