package com.napier.sem;
import java.sql.*;
import java.util.ArrayList;

public class WorldQuery {

    private final Connection con;

    public WorldQuery(Connection con) {
        this.con = con;
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

    // Method to get top N populated countries in the world
    public ArrayList<Country> getTopPopulatedCountries(int N) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital " +
                            "FROM country " +
                            "ORDER BY Population DESC " +
                            "LIMIT " + N; // Order by population and limit the result to N rows
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> topCountries = new ArrayList<>();
            while (rset.next()) {
                Country ctry = new Country();
                ctry.code = rset.getString("Code");
                ctry.name = rset.getString("Name");
                ctry.continent = rset.getString("Continent");
                ctry.region = rset.getString("Region");
                ctry.population = rset.getInt("Population");
                ctry.capital = rset.getString("Capital");
                topCountries.add(ctry);
            }
            return topCountries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated countries");
            return null;
        }
    }


}
