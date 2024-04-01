package com.napier.sem;
import java.sql.*;

public class App {

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
       a.connect();

        // Get countries by population
        Country country = a.getCountriesByPopulation();

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
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen");
            }
        }
    }


    public Country getCountriesByPopulation() {
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
            // Return new country instance if valid.
            // Check one is returned
            if (rset.next()) {
                Country country = new Country();
                country.code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.population = rset.getInt("Population");
                country.capital = rset.getString("Capital");
               return country;
            }

            else
                return null;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by population");
            return null;
        }
    }

    public void displayCountry(Country country) {
        if (country != null) {
            System.out.println(
                            "Code: " + country.code + "\n" + "Name: " + country.name + "\n" +
                            "Continent: " + country.continent + "\n" +
                            "Region: " + country.region + "\n" +
                            "Population: " + country.population + "\n" +
                            "Capital: " + country.capital + "\n"
            );
        }
    }

    public void disconnect()
    {
        if (con!= null)
        {
            try {
                // Close connection
                con.close();
            }

            catch (Exception e)
            {
                System.out.println("Error closing connection to Database");
            }
        }
    }
}
