package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AppIntegrationTest {

    static App app;

    // create new app and connect the database
    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 30000);
    }

    // test for 32 issues
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
        app.CitiesInCountry("India");
    }

    @Test
    void testCitiesInDistrict (){
        app.CitiesInDistrict("Texas");
    }

    @Test
    void testTopCitiesInWorld (){
        app.TopCitiesInWorld(4);
    }

    @Test
    void testTopCitiesInContinent (){
        app.TopCitiesInContinent("Asia", 3);
    }

    @Test
    void testTopCitiesInRegion (){
        app.TopCitiesInRegion("South America", 3);
    }
    @Test
    void testTopCitiesInCountry (){
        app.TopCitiesInCountry("United Kingdom", 3);
    }
    @Test
    void testTopCitiesInDistrict (){
        app.TopCitiesInDistrict("Miami", 3);
    }

    @Test
    void testCapitalCitiesInWorld (){
        app.CapitalCitiesInWorld();
    }

    @Test
    void testCapitalCitiesInContinent ()
    {
        app.CapitalCitiesInContinent("Asia");
    }

    @Test
    void testCapitalCitiesInRegion (){
        app.CapitalCitiesInRegion("North America");
    }

    @Test
    void testTopCapitalCitiesInWorld (){
        app.TopCapitalCitiesInWorld(3);
    }

    @Test
    void testTopCapitalCitiesInContinent (){
        app.TopCapitalCitiesInContinent("Europe", 4);
    }

    @Test
    void testTopCapitalCitiesInRegion (){
        app.TopCapitalCitiesInRegion("North America", 3);
    }

    @Test
    void testPopulationInContinents () {
        app.PopulationInContinents();
    }

    @Test
    void testPopulationInRegions () {
        app.PopulationInRegions();
    }

    @Test
    void testPopulationInCountries () {
        app.PopulationInCountries();
    }

    @Test
    void testTotalPopulationInWorld () {
        app.TotalPopulationInWorld();
    }

    @Test
    void testTotalPopulationInContinent () {
        app.TotalPopulationOfContinent("Europe");
    }

    @Test
    void testTotalPopulationInRegion () {
        app.TotalPopulationOfRegion("South America");
    }

    @Test
    void testTotalPopulationInDistict () {
        app.TotalPopulationOfDistrict("England");
    }
    @Test
    void testTotalPopulationInCountry () {
        app.TotalPopulationOfCountry("United States");
    }

    @Test
    void testTotalPopulationInCity () {
        app.TotalPopulationOfCity("London");
    }

    @Test
    void testLanguageReport () {
        app.languageReport();
    }
}


