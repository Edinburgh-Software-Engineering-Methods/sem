package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        //Create new Application and connect to database

        App a = new App();

        if (args.length < 1) {
            a.connect("localhost:33060", 10000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        // Language Report
        a.languageReport();

        // Get population of world, continent, region, country and city

        a.TotalPopulationInWorld();
        a.TotalPopulationOfContinent("Asia");
        a.TotalPopulationOfRegion("North America");
        a.TotalPopulationOfDistrict("California");
        a.TotalPopulationOfCountry("China");
        a.TotalPopulationOfCity("New York");


        // Get population analysis by each continent, region and country
       /* a.PopulationInContinents();
        a.PopulationInRegions();
        a.PopulationInCountries(); */

        //Get Top N Capital Cities in world, continent and region
       /* a.TopCapitalCitiesInWorld(4);
        a.TopCapitalCitiesInContinent("Africa" , 5);
        a.TopCapitalCitiesInRegion("Middle East", 3);*/

        // Get Capital Cities in world, continent and region
       /*  a.CapitalCitiesInWorld();
        a.CapitalCitiesInContinent("Asia");
        a.CapitalCitiesInRegion("North America");*/

        // Get Top N cities in world, continent, region, country and district
       /* a.TopCitiesInWorld(5);
        a.TopCitiesInContinent("Europe", 3);
        a.TopCitiesInRegion("South East Asia", 5);
        a.TopCitiesInCountry("United States", 5);
        a.TopCitiesInDistrict("Florida", 6);*/

        //Get Cities by Population in World, Continent and Region, District and Country
        /* a.CitiesInWorld();
        a.CitiesInContinent("Africa");
        a.CitiesInRegion("South America");
        a.CitiesInCountry("Myanmar");
        a.CitiesInDistrict("Texas"); */

        // Get topN countries in World, Continent and Region
        /* a.TopCountriesInWorld(5);
        a.TopCountriesByContinent("Asia" , 5);
        a.TopCountriesByRegion("North America", 5);*/

        // Get countries by population in world, continent and region
        /* a.CountriesInWorld();
        a.CountriesByContinent("Asia");
        a.CountriesByRegion("North America"); */


        // Disconnect from database
        a.disconnect();
    }

    private Connection con = null;

    // connection to database
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean shouldWait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    // Wait a bit for db to start
                    Thread.sleep(delay);
                }

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());

                // Let's wait before attempting to reconnect
                shouldWait = true;
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    public void CountriesInWorld()
    {
        WorldQuery worldQuery = new WorldQuery(con);
        ArrayList<Country> countriesByPopulation = worldQuery.getCountriesByPopulation();
        System.out.println("All the countries in the world by population: ");
        displayCountry(countriesByPopulation);
    }

    public void CountriesByContinent (String continent)
    {
        ContinentQuery continentQuery = new ContinentQuery(con);
        ArrayList<Country> countriesByContinent = continentQuery.getCountriesByContinent(continent);
        System.out.println("All the countries in " + continent + " by population: ");
        displayCountry(countriesByContinent);
    }

    public void CountriesByRegion (String region)
    {
        RegionQuery regionQuery = new RegionQuery(con);
        ArrayList<Country> countriesByRegion = regionQuery.getCountriesByRegion(region);
        System.out.println("All the countries in " + region + " by population: ");
        displayCountry(countriesByRegion);
    }

    public void TopCountriesInWorld (int N)
    {
        WorldQuery worldQuery = new WorldQuery(con);
        ArrayList<Country> topPopulatedCountries = worldQuery.getTopPopulatedCountries(N);
        System.out.println("Top" + " Populated Countries in the World: ");
        displayCountry(topPopulatedCountries);
    }

    public void TopCountriesByContinent (String continent, int N)
    {
        ContinentQuery continentQuery = new ContinentQuery(con);
        ArrayList<Country> topCountriesByContinent = continentQuery.getTopCountriesByContinent(continent , N);
        System.out.println("Top" + N + " Populated Countries in " +  continent);
        displayCountry(topCountriesByContinent);
    }

    public void TopCountriesByRegion (String region, int N)
    {
        RegionQuery regionQuery = new RegionQuery(con);
        ArrayList<Country> topCountriesByRegion = regionQuery.getTopCountriesByRegion(region, N);
        System.out.println("Top" + N + " Populated Countries in " +  region);
        displayCountry(topCountriesByRegion);
    }

    public void CitiesInWorld ()
    {
        WorldQuery worldQuery = new WorldQuery(con);
        ArrayList<City> citiesByPopulation = worldQuery.getCitiesByPopulation();
        System.out.println("All cities in the world by population: ");
        displayCity(citiesByPopulation);
    }

    public void CitiesInContinent (String continent)
    {
        ContinentQuery continentQuery = new ContinentQuery(con);
        ArrayList<City> citiesByContinent = continentQuery.getCitiesByContinent(continent);
        System.out.println("All cities in " + continent + " by population: ");
        displayCity(citiesByContinent);

    }

    public void CitiesInRegion (String region)
    {
        RegionQuery regionQuery = new RegionQuery(con);
        ArrayList<City> citiesByRegion =regionQuery.getCitiesByRegion(region);
        System.out.println("All cities in " + region +" by population: ");
        displayCity(citiesByRegion);
    }

    public void CitiesInCountry (String country)
    {
        CountryQuery countryQuery = new CountryQuery(con);
        ArrayList<City> citiesByCountry = countryQuery.getCitiesByCountry(country);
        System.out.println("All cities in " + country +" by population: ");
        displayCity(citiesByCountry);
    }

    public void CitiesInDistrict (String district)
    {
        DistrictQuery districtQuery = new DistrictQuery(con);
        ArrayList<City> citiesByDistrict = districtQuery.getCitiesByDistrict(district);
        System.out.println("All cities in " + district + " by population: ");
        displayCity(citiesByDistrict);

    }

    public void TopCitiesInWorld (int N)
    {
        WorldQuery worldQuery = new WorldQuery(con);
        ArrayList<City> topNCitiesByPopulation = worldQuery.getTopNCitiesByPopulation(N);
        System.out.println("Top" + N + " Populated Cities in the World: ");
        displayCity(topNCitiesByPopulation);
    }

    public void TopCitiesInContinent (String continent, int N)
    {
        ContinentQuery continentQuery = new ContinentQuery(con);
        ArrayList<City> topCitiesByContinent = continentQuery.getTopCitiesByContinent(continent, N);
        System.out.println("Top" + N + " Cities in " + continent);
        displayCity(topCitiesByContinent);
    }

    public void TopCitiesInRegion (String region, int N)
    {
        RegionQuery regionQuery = new RegionQuery(con);
        ArrayList<City> topCitiesByRegion = regionQuery.getTopCitiesByRegion(region, N);
        System.out.println("Top" + N + " Cities in " + region);
        displayCity(topCitiesByRegion);
    }

    public void TopCitiesInCountry (String country, int N)
    {
        CountryQuery countryQuery = new CountryQuery(con);
        ArrayList<City> topCitiesByCountry = countryQuery.getTopCitiesByCountry(country, N);
        System.out.println("Top" + N + " Cities in " + country);
        displayCity(topCitiesByCountry);
    }

    public void TopCitiesInDistrict (String district, int N)
    {
        DistrictQuery districtQuery = new DistrictQuery(con);
        ArrayList<City> topCitiesByDistrict = districtQuery.getTopCitiesByDistrict(district, N);
        System.out.println("Top" + N + " Cities in " + district);
        displayCity(topCitiesByDistrict);

    }
    public void CapitalCitiesInWorld() {
        WorldQuery worldQuery = new WorldQuery(con);
        ArrayList<Country> CapitalCitiesByPopulation = worldQuery.getCapitalCitiesByPopulation();
        System.out.println("Top populated capital cities in the World");
        displayCapitalCity(CapitalCitiesByPopulation);
    }

    public void CapitalCitiesInContinent(String continent){
        ContinentQuery continentQuery = new ContinentQuery(con);
        ArrayList<Country> capitalCitiesByContinent = continentQuery.getCapitalCitiesByContinent(continent);
        System.out.println("All Capital Cities in " + continent);
        displayCapitalCity(capitalCitiesByContinent);
    }

    public void CapitalCitiesInRegion (String region){
        RegionQuery regionQuery = new RegionQuery(con);
        ArrayList<Country> capitalCitiesByRegion = regionQuery.getCapitalCitiesByRegion(region);
        System.out.println("All Capital Cities in " + region);
        displayCapitalCity(capitalCitiesByRegion);
    }

    public void TopCapitalCitiesInWorld (int N)
    {
        WorldQuery worldQuery = new WorldQuery(con);
        ArrayList<Country> topNCapitalCitiesByPopulation = worldQuery.getTopNCapitalCitiesByPopulation(5);
        System.out.println("Top " + N + " Populated Capital Cities in the World");
        displayCapitalCity(topNCapitalCitiesByPopulation);
    }

    public void TopCapitalCitiesInContinent (String continent, int N)
    {
        ContinentQuery continentQuery = new ContinentQuery(con);
        ArrayList<Country> topCapitalCitiesByContinent = continentQuery.getTopCapitalCitiesByContinent(continent, N);
        System.out.println("Top " + N + " populated capital cities in " + continent);
        displayCapitalCity(topCapitalCitiesByContinent);
    }

    public void TopCapitalCitiesInRegion (String region, int N)
    {
        RegionQuery regionQuery = new RegionQuery(con);
        ArrayList<Country> topCapitalCitiesByRegion = regionQuery.getTopCapitalCitiesByRegion(region, N);
        System.out.println("Top " + N + " populated capital cities in " + region);
        displayCapitalCity(topCapitalCitiesByRegion);
    }

    public void PopulationInContinents () {
        ContinentQuery continentQuery = new ContinentQuery(con);
        ArrayList<Population> populationByContinent = continentQuery.getPopulationByContinent();
        System.out.println("Population Analysis by each continents in the World");
        displayPopulation(populationByContinent);
    }

    public void PopulationInRegions () {
        RegionQuery regionQuery = new RegionQuery(con);
        ArrayList<Population> populationByRegion = regionQuery.getPopulationByRegion();
        System.out.println("Population Analysis by each regions in the World ");
        displayPopulation(populationByRegion);
    }

    public void PopulationInCountries () {
        CountryQuery countryQuery = new CountryQuery(con);
        ArrayList<Population> populationByCountry = countryQuery.getPopulationByCountry();
        System.out.println("Population Analysis of each countries in the World");
        displayPopulation(populationByCountry);
    }

    public void TotalPopulationInWorld () {
        WorldQuery worldQuery = new WorldQuery(con);
        long population = worldQuery.getWorldPopulation();
        System.out.println("Total Population in World: " + population);
    }

    public void TotalPopulationOfContinent (String continent) {
        ContinentQuery continentQuery = new ContinentQuery(con);
        long population = continentQuery.getContinentPopulation(continent);
        System.out.println(("Total Population in Continent (" + continent + ") :" + population));
    }

    public void TotalPopulationOfRegion (String region) {
        RegionQuery regionQuery = new RegionQuery(con);
        long population = regionQuery.getRegionPopulation(region);
        System.out.println(("Total Population in Region (" + region + ") :" + population));
    }

    public void TotalPopulationOfDistrict (String district) {
        DistrictQuery districtQuery = new DistrictQuery(con);
        long population = districtQuery.getDistrictPopulation(district);
        System.out.println(("Total Population in District (" + district + ") :" + population));
    }

    public void TotalPopulationOfCountry (String country) {
        CountryQuery countryQuery = new CountryQuery(con);
        long population = countryQuery.getCountryPopulation(country);
        System.out.println(("Total Population in Country (" + country + ") :" + population));
    }

    public void TotalPopulationOfCity ( String city) {
        WorldQuery worldQuery = new WorldQuery(con);
        long population = worldQuery.getCityPopulation(city);
        System.out.println(("Total Population in City (" + city + ") :" + population));
    }

    public void languageReport() {
        WorldQuery worldQuery = new WorldQuery(con);
        ArrayList<Population> languagePopulation = worldQuery.getLanguageReport();
        long worldPopulation = worldQuery.getWorldPopulation();

        System.out.println("Language Report:");
        System.out.println("---------------------------------------");
        System.out.printf("%-15s %-15s %-15s%n", "Language", "Total Population", "Percentage");
        System.out.println("---------------------------------------");

        for (Population language : languagePopulation) {
            double percentage = ((double) language.totalPopulation / worldPopulation) * 100;
            System.out.printf("%-15s %-15d %.2f%%%n", language.name, language.totalPopulation, percentage);
        }
    }
    /**
     * Prints a list of countries.
     *
     * @param countries The country to print.
     */
    public void displayCountry(ArrayList<Country> countries) {
        System.out.printf("%-5s %-25s %-15s %-20s %-15s %-10s%n", "Code", "Name", "Continent", "Region", "Capital", "Population");
        for (Country ctry : countries) {
            String ctry_String = String.format("%-5s %-25s %-15s %-20s %-15s %-10d", ctry.code, ctry.name, ctry.continent, ctry.region, ctry.capital, ctry.population);
            System.out.println(ctry_String);
        }
    }

    /**
     * Prints a list of cities.
     *
     * @param cities The list of cities to print.
     */
    public void displayCity(ArrayList<City> cities) {
        System.out.printf("%-25s %-25s %-15s %-10s%n", "City", "Country", "District", "Population");
        for (City city : cities) {
            String cityString = String.format("%-25s %-25s %-15s %-10d", city.name, city.country, city.district, city.population);
            System.out.println(cityString);
        }
    }

    /**
     * Prints a list of cities.
     *
     * @param countries The list of cities to print.
     */
    public void displayCapitalCity(ArrayList<Country> countries) {
        System.out.printf("%-25s %-25s %-15s%n", "Capital", "Country", "Population");
        for (Country ctry : countries) {
            String ctry_String = String.format("%-25s %-25s %-15d", ctry.capital, ctry.name, ctry.population);
            System.out.println(ctry_String);
        }
    }

    /**
     * Prints a list of cities.
     *
     * @param populations The list of cities to print.
     */
    // Display population report
    public void displayPopulation(ArrayList<Population> populations) {
        System.out.printf("%-25s %-20s %-20s %-20s%n", "Name", "Total Population", "Population in Cities(including %)", "Population not in Cities(including %)");
        for (Population pop : populations) {
            double percentageInCities = ((double) pop.populationInCities / pop.totalPopulation) * 100.0;
            double percentageNotInCities = ((double) pop.populationNotInCities / pop.totalPopulation) * 100.0;
            String formattedString = String.format("%-25s %-20d   %-20d %.2f%%        %-20d %.2f%%",
                    pop.name, pop.totalPopulation, pop.populationInCities, percentageInCities, pop.populationNotInCities, percentageNotInCities);
            System.out.println(formattedString);
        }
    }


    // disconnect from database
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to Database");
            }
        }
    }
}
