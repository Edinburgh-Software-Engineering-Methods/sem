package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Get countries by population
        ArrayList<Country> country = a.getCountriesByPopulation();

        // Display country information
        a.displayCountry(country);

        // Disconnect from database
        a.disconnect();
    }

    private Connection con = null;

    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {

                Thread.sleep(30000);

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false",
                        "root", "example"); // Change the database name to "world"
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen");
            }
        }
    }

    public ArrayList<Country> getCountriesByPopulation() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital " +
                            "FROM country " +
                            "ORDER BY Population DESC"; // Order by population from largest to smallest
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next()) {
                Country ctry = new Country();
                ctry.code = rset.getString("Code");
                ctry.name = rset.getString("Name");
                ctry.continent = rset.getString("Continent");
                ctry.region = rset.getString("Region");
                ctry.population = rset.getInt("Population");
                ctry.capital = rset.getString("Capital");
                countries.add(ctry);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by population");
            return null;
        }
    }

    /**
     * Prints a list of countries.
     * @param country The country to print.
     */
    public void displayCountry(ArrayList<Country> country) {

            // Print header
            System.out.println(String.format("%-5s %-25s %-15s %-20s %-10s", "Code", "Name", "Continent", "Region", "Population"));
            // Print country information
        for (Country ctry: country)
        {
            String ctry_String =
                    String.format("%-5s %-25s %-15s %-20s %-10d",
                            ctry.code, ctry.name, ctry.continent, ctry.region, ctry.population);
            System.out.println(ctry_String);
        }
    }

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
