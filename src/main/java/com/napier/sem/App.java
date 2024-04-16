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

        //connect with WorldQuery, ContinentQuery, Region Query, DistrictQuery and CountryQuery Classes

        WorldQuery worldQuery = new WorldQuery(a.con);
        ContinentQuery continentQuery = new ContinentQuery(a.con);
        RegionQuery regionQuery = new RegionQuery(a.con);
        CountryQuery countryQuery = new CountryQuery(a.con);
        DistrictQuery districtQuery = new DistrictQuery(a.con);

        // Get Capital Cities in world, continent and region
       ArrayList<Country> capitalCitiesByPopulation = worldQuery.getCapitalCitiesByPopulation();
        System.out.println("All Capital Cities in the world:  ");
        a.displayCapitalCity(capitalCitiesByPopulation);

        ArrayList<Country> capitalCitiesByContinent = continentQuery.getCapitalCitiesByContient("Europe");
        System.out.println("All Capital Cities in Europe");
        a.displayCapitalCity(capitalCitiesByContinent);

        ArrayList<Country> capitalCitiesByRegion = regionQuery.getCapitalCitiesByRegion("Eastern Africa");
        System.out.println("All Capital Cities in Eastern Africa");
        a.displayCapitalCity(capitalCitiesByRegion);

        // Get Top N cities in world, continent, region, country and district
       /* ArrayList<City> topNCitiesByPopulation = worldQuery.getTopNCitiesByPopulation(10);
        System.out.println("Top 10 Populated Cities in the World: ");
        a.displayCity(topNCitiesByPopulation);

       ArrayList<City> topCitiesByContinent = continentQuery.getTopCitiesByContinent("Asia" , 5);
       System.out.println("Top 5 Cities in Asia");
       a.displayCity(topCitiesByContinent);

       ArrayList<City> topCitiesByRegion = regionQuery.getTopCitiesByRegion("Eastern Europe" , 5);
       System.out.println("Top 5 Cities in Eastern Europe");
       a.displayCity(topCitiesByRegion);

       ArrayList<City> topCitiesByCountry = countryQuery.getTopCitiesByCountry("USA" , 7);
       System.out.println("Top 7 Cities in USA");
       a.displayCity(topCitiesByCountry);

       ArrayList<City> topCitiesByDistrict = districtQuery.getTopCitiesByDistrict("Florida" , 3);
       System.out.println("Top 3 Cities in Florida");
       a.displayCity(topCitiesByDistrict);*/

        //Get Cities by Population in World, Continent and Region, District and Country
        /* ArrayList<City> citiesByPopulation = worldQuery.getCitiesByPopulation();
        System.out.println("All cities in the world by population: ");
        a.displayCity(citiesByPopulation);

        ArrayList<City> citiesByContinent = continentQuery.getCitiesByContinent("Africa");
        System.out.println("All cities in Africa by population: ");
        a.displayCity(citiesByContinent);

        ArrayList<City> citiesByRegion =regionQuery.getCitiesByRegion("South America");
        System.out.println("All cities in South America by population: ");
        a.displayCity(citiesByRegion);

        ArrayList<City> citiesByCountry = countryQuery.getCitiesByCountry("United Kingdom");
        System.out.println("All cities in United Kingdom by population: ");
        a.displayCity(citiesByCountry);

        ArrayList<City> citiesByDistrict = districtQuery.getCitiesByDistrict("Texas");
        System.out.println("All cities in Texas by population: ");
        a.displayCity(citiesByDistrict); */

        // Get topN countries in World, Continent and Region

        /* ArrayList<Country> topPopulatedCountries = worldQuery.getTopPopulatedCountries(10);
        System.out.println("Top 10 Populated Countries in the World: ");
        a.displayCountry(topPopulatedCountries);

        ArrayList<Country> topCountriesByContinent = continentQuery.getTopCountriesByContinent("Europe", 6);
        System.out.println("Top 6 Populated Countries in Europe: ");
        a.displayCountry(topCountriesByContinent);

        ArrayList<Country> topCountriesByRegion = regionQuery.getTopCountriesByRegion("Southeast Asia", 5);
        System.out.println("Top 5 Populated Countries in Southeast Asia: ");
        a.displayCountry(topCountriesByRegion); */


        // Get countries by population in world, continent and region
       /* ArrayList<Country> countriesByPopulation = worldQuery.getCountriesByPopulation();
        System.out.println("All the countries in the world by population: ");
        a.displayCountry(countriesByPopulation);

        ArrayList<Country> countriesByContinent = continentQuery.getCountriesByContinent("Asia");
        System.out.println("All the countries in Asia by population: ");
        a.displayCountry(countriesByContinent);

        ArrayList<Country> countriesByRegion = regionQuery.getCountriesByRegion("North America");
        System.out.println("All the countries in North America by population: ");
        a.displayCountry(countriesByRegion); */

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
