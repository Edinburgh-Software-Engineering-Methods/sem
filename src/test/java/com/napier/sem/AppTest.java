package com.napier.sem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class AppTest {

    private App app;

    @BeforeEach
    public void setUp() {
        app = new App();
    }

    @Test
    public void testConnect() {
        try {
            app.connect("localhost:33060", 10000);
        } catch (Exception e) {
            fail("Exception thrown while connecting to the database: " + e.getMessage());
        }

        // Use reflection to access the con field
        try {
            Field conField = App.class.getDeclaredField("con");
            conField.setAccessible(true);
            Connection connection = (Connection) conField.get(app);
            assertNotNull(connection, "Connection should not be null after successful connection");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to access the con field in the App class");
        }
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

    @AfterEach
    public void tearDown() {
        app.disconnect();
    }
}