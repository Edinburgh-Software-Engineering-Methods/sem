package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AppIntegrationTest {

    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 30000);
    }

    @Test
    void testCountriesInWorld() {
        app.CountriesInWorld();
    }

    @Test
    void testCountriesByContinent() {
        app.CountriesByContinent("Asia");
    }

    @Test
    void testCountriesByRegion() {
        app.CountriesByRegion("Southeast Asia");
    }

    @Test
    void testTopCountriesInWorld() {
        app.TopCountriesInWorld(5);
    }

    @Test
    void testTopCountriesByContinent() {
        app.TopCountriesByContinent("Asia" , 3);
    }

    @Test
    void testTopCountriesByRegion(){
        app.TopCountriesByRegion("North America" , 3);
    }

    @Test
    void testCitiesInWorld (){
        app.CitiesInWorld();
    }

    @Test
    void testCitiesInContinent ()
    {
        app.CitiesInContinent("Europe");
    }

    @Test
    void testCitiesInRegion (){
        app.CitiesInRegion("South America");
    }

    @Test
    void testCitiesInCountry () {
        app.CitiesInCountry("England");
    }

    @Test
    void testCitiesInDistrict (){
        app.CitiesInDistrict("Texas");
    }

}