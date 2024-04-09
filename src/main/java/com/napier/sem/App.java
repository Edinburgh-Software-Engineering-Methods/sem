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

        /* connect with WorldQuery, ContinentQuery, Region Query, DistrictQuery and CountryQuery Classes */

        WorldQuery worldQuery = new WorldQuery(a.con);
        ContinentQuery continentQuery = new ContinentQuery(a.con);
        RegionQuery regionQuery = new RegionQuery(a.con);
        CountryQuery countryQuery = new CountryQuery(a.con);
        DistrictQuery districtQuery = new DistrictQuery(a.con);

        //Get Cities by Population in World, Continent and Region, District and Country
        ArrayList<City> citiesByPopulation = worldQuery.getCitiesByPopulation();
        System.out.println("All cities in the world by population: ");
        a.displayCity(citiesByPopulation);

        ArrayList<City> citiesByContinent = continentQuery.getCitiesByContinent("Africa");
        System.out.println("\nAll cities in Africa by population: ");
        a.displayCity(citiesByContinent);

        ArrayList<City> citiesByRegion =regionQuery.getCitiesByRegion("South America");
        System.out.println("\nAll cities in South America by population: ");
        a.displayCity(citiesByRegion);

        ArrayList<City> citiesByCountry = countryQuery.getCitiesByCountry("GBR");
        System.out.println("All cities in United Kingdom by population: ");
        a.displayCity(citiesByCountry);

        ArrayList<City> citiesByDistrict = districtQuery.getCitiesByDistrict("Texas");
        System.out.println("All cities in Texas by population: ");
        a.displayCity(citiesByDistrict);

        // Get topN countries in World, Continent and Region
        /*ArrayList<Country> topPopulatedCountries = worldQuery.getTopPopulatedCountries(10);
        System.out.println("Top 10 Populated Countries in the World: ");
        a.displayCountry(topPopulatedCountries);

        ArrayList<Country> topCountriesByContinent = continentQuery.getTopCountriesByContinent("Europe", 6);
        System.out.println("Top 6 Populated Countries in Europe: ");
        a.displayCountry(topCountriesByContinent);

        ArrayList<Country> topCountriesByRegion = regionQuery.getTopCountriesByRegion("Southeast Asia", 3);
        System.out.println("Top 3 Populated Countries in Southeast Asia: ");
        a.displayCountry(topCountriesByRegion);*/


        // Get countries by population in world, continent and region
       /* ArrayList<Country> countriesByPopulation = a.getCountriesByPopulation();
        System.out.println("All the countries in the world by population: ");
        a.displayCountry(countriesByPopulation);

        ArrayList<Country> countriesByContinent = a.getCountriesByContinent("Asia");
        System.out.println("All the countries in Asia by population: ");
        a.displayCountry(countriesByContinent);

        ArrayList<Country> countriesByRegion = a.getCountriesByRegion("North America");
        System.out.println("All the countries in North America by population: ");
        a.displayCountry(countriesByRegion);*/

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
    /**
     * Prints a list of countries.
     *
     * @param country The country to print.
     */
    public void displayCountry(ArrayList<Country> country) {
        System.out.println(String.format("%-5s %-25s %-15s %-20s %-10s", "Code", "Name", "Continent", "Region", "Population"));
        for (Country ctry : country) {
            String ctry_String = String.format("%-5s %-25s %-15s %-20s %-10d", ctry.code, ctry.name, ctry.continent, ctry.region, ctry.population);
            System.out.println(ctry_String);
        }
    }

    /**
     * Prints a list of cities.
     *
     * @param city The list of cities to print.
     */
    public void displayCity(ArrayList<City> city) {
        System.out.println(String.format("%-35s %-35s %-20s %-15s", "Name", "Country", "District", "Population"));
        for (City cityData : city) {
            String cityString = String.format("%-35s %-35s %-20s %-15d",
                    cityData.name, cityData.country, cityData.district, cityData.population);
            System.out.println(cityString);
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
