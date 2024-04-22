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

        Department dept = a.getDepartment("Development");
        ArrayList<Employee> employees = a.getSalariesByDepartment(dept);



        //Get Top N Capital Cities in world, continent and region
        a.TopCapitalCitiesInWorld(4);
        a.TopCapitalCitiesInContinent("Africa" , 5);
        a.TopCapitalCitiesInRegion("Middle East", 3);

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


    /**
     * Prints a list of countries.
     *
     * @param countries The country to print.
     */
    public void displayCountry(ArrayList<Country> countries) {
        System.out.println(String.format("%-5s %-25s %-15s %-20s %-15s %-10s", "Code", "Name", "Continent", "Region", "Capital", "Population"));
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
        System.out.println(String.format("%-25s %-25s %-15s %-10s", "City", "Country", "District", "Population"));
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
        System.out.println(String.format("%-25s %-25s %-15s", "Capital", "Country", "Population"));
        for (Country ctry : countries) {
            String ctry_String = String.format("%-25s %-25s %-15d", ctry.capital, ctry.name, ctry.population);
            System.out.println(ctry_String);
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
