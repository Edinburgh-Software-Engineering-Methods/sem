package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {

    // main method for displaying countries list
    public static void main(String[] args) {
        // Create new Application and connect to database
        App a = new App();

        if (args.length < 1) {
            a.connect("localhost:33060", 10000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        // Get countries by population
        ArrayList<Country> countriesByPopulation = a.getCountriesByPopulation();
        System.out.println("All the countries in the world by population: ");
        // Display country information
        a.displayCountry(countriesByPopulation);

        ArrayList<Country> countriesByContinent = a.getCountriesByContinent("Asia");
        System.out.println("All the countries in Asia by population: ");
        a.displayCountry(countriesByContinent);

        ArrayList<Country> countriesByRegion = a.getCountriesByRegion("North America");
        System.out.println("All the countries in North America by population: ");
        a.displayCountry(countriesByRegion);

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

    //get all the countries in the world by population

    public ArrayList<Country> getCountriesByPopulation() {
        WorldQuery worldQuery = new WorldQuery(con);
        return worldQuery.getCountriesByPopulation();
    }

    // get all the countries in a continent ordered by population
    public ArrayList<Country> getCountriesByContinent(String continent) {
        ContinentQuery continentQuery = new ContinentQuery(con);
        return continentQuery.getCountriesByContinent(continent);
    }
    // get all the countries in a region ordered by population
    public ArrayList<Country> getCountriesByRegion(String region) {
        RegionQuery regionQuery = new RegionQuery(con);
        return regionQuery.getCountriesByRegion(region);
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
