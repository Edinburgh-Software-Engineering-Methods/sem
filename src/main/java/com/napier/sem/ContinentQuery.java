package com.napier.sem;
import java.sql.*;
import java.util.ArrayList;
public class ContinentQuery {
    private final Connection con;

    public ContinentQuery(Connection con) {
        this.con = con;
    }

    public ArrayList<Country> getCountriesByContinent(String continent) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci ON c.Capital = ci.ID " +
                    "WHERE c.Continent = '" + continent + "' " +
                    "ORDER BY c.Population DESC";
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next()) {
                // Create a new instance of Country using the constructor with arguments
                Country ctry = new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getInt("Population"),
                        rset.getString("Capital")
                );
                countries.add(ctry);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by continent");
            return null;
        }
    }

    public ArrayList<Country> getTopCountriesByContinent(String continent, int N) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci ON c.Capital = ci.ID " +
                    "WHERE c.Continent = '" + continent + "' " +
                    "ORDER BY c.Population DESC " +
                    "LIMIT " + N; // Order by population and extracting top N number

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Extract country information
            ArrayList<Country> topCountries = new ArrayList<>();
            while (rset.next()) {
                // Create a new instance of Country using the constructor with arguments
                Country ctry = new Country(
                        rset.getString("Code"),
                        rset.getString("Name"),
                        rset.getString("Continent"),
                        rset.getString("Region"),
                        rset.getInt("Population"),
                        rset.getString("Capital")
                );
                topCountries.add(ctry);
            }
            return topCountries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by population in" + continent);
            return null;
        }
    }

    public ArrayList<City> getCitiesByContinent(String continent) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT ci.Name AS City, c.Name AS Country, ci.District, ci.Population " +
                    "FROM city ci " +
                    "JOIN country c ON ci.CountryCode = c.Code " +
                    "WHERE c.Continent = '" + continent + "' " +
                    "ORDER BY ci.Population DESC";// Order by population from largest to smallest
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<City> cities = new ArrayList<>();
            while (rset.next()) {
                City cityData = new City(
                        rset.getString("City"),
                        rset.getString("Country"),
                        rset.getString("District"),
                        rset.getInt("Population")
                );
                cities.add(cityData);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities by population in" + continent);
            return null;
        }
    }


    public ArrayList<City> getTopCitiesByContinent(String continent, int N) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT ci.Name AS City, c.Name AS Country, ci.District, ci.Population " +
                    "FROM city ci " +
                    "JOIN country c ON ci.CountryCode = c.Code " +
                    "WHERE c.Continent = '" + continent + "' " +
                    "ORDER BY ci.Population DESC " +
                    "LIMIT " + N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<>();
            while (rset.next()) {
                City cityData = new City(
                        rset.getString("City"),
                        rset.getString("Country"),
                        rset.getString("District"),
                        rset.getInt("Population")
                );
                cities.add(cityData);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top " + N + " cities by population in " + continent);
            return null;
        }
    }

    public ArrayList<Country> getCapitalCitiesByContinent(String continent) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT c.Name, c.Population, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci ON c.Capital = ci.ID " +
                    "WHERE c.Continent = '" + continent + "' " +
                    "ORDER BY c.Population DESC";
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next()) {
                // Create a new instance of Country using the constructor with arguments
                Country ctry = new Country(
                        rset.getString("Name"),
                        rset.getInt("Population"),
                        rset.getString("Capital")
                );
                countries.add(ctry);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by continent");
            return null;
        }
    }

    public ArrayList<Country> getTopCapitalCitiesByContinent(String continent, int N) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT c.Name, c.Population, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci ON c.Capital = ci.ID " +
                    "WHERE c.Continent = '" + continent + "' " +
                    "ORDER BY c.Population DESC " +
                    "LIMIT " + N;
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next()) {
                // Create a new instance of Country using the constructor with arguments
                Country ctry = new Country(
                        rset.getString("Name"),
                        rset.getInt("Population"),
                        rset.getString("Capital")
                );
                countries.add(ctry);
            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top" + N + "cities in " + continent);
            return null;
        }
    }
}



