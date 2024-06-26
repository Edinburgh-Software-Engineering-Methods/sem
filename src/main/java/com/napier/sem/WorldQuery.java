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
                    "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                            "FROM country c " +
                            "LEFT JOIN city ci ON c.Capital = ci.ID " +
                            "ORDER BY c.Population DESC"; // Order by population from largest to smallest
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
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
            String strSelect = "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci ON c.Capital = ci.ID " +
                    "ORDER BY c.Population DESC " +
                    "LIMIT " + N; // Order by population and extract top N numbers
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> topCountries = new ArrayList<>();
            while (rset.next()) {
                // Create a new instance of Country from Country Class
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
            System.out.println("Failed to get top populated countries");
            return null;
        }
    }

    //Method to get top Cities in the World
    public ArrayList<City> getCitiesByPopulation() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ci.Name AS City, c.Name AS Country, ci.District, ci.Population " +
                            "FROM city ci " +
                            "JOIN country c ON ci.CountryCode = c.Code " +
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
            System.out.println("Failed to get cities by population");
            return null;
        }
    }

    public ArrayList<City> getTopNCitiesByPopulation(int N) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ci.Name AS City, c.Name AS Country, ci.District, ci.Population " +
                            "FROM city ci " +
                            "JOIN country c ON ci.CountryCode = c.Code " +
                            "ORDER BY ci.Population DESC " +
                            "LIMIT " + N; // order by population and use top N input
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
            System.out.println("Failed to get top " + N + " cities by population");
            return null;
        }
    }

    public ArrayList<Country> getCapitalCitiesByPopulation() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT c.Name, c.Population, ci.Name AS Capital " +
                            "FROM country c " +
                            "LEFT JOIN city ci ON c.Capital = ci.ID " +
                            "ORDER BY c.Population DESC"; // Order by population from largest to smallest
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next()) {
                // Create a new instance of Country from Country Class
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
            System.out.println("Failed to get capital cities by population");
            return null;
        }
    }

    public ArrayList<Country> getTopNCapitalCitiesByPopulation(int N) {
        try {
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT c.Name, c.Population, ci.Name AS Capital " +
                            "FROM country c " +
                            "LEFT JOIN city ci ON c.Capital = ci.ID " +
                            "ORDER BY c.Population DESC " +
                            "LIMIT " + N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> countries = new ArrayList<>();
            while (rset.next()) {
                // Create a new instance of Country using the constructor from Country class
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
            System.out.println("Failed to get top populated capital cities in the world");
            return null;
        }
    }

    public long getWorldPopulation() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT SUM(Population) AS WorldPopulation FROM country");
            if (rs.next()) {
                return rs.getLong("WorldPopulation");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get populations of the world");
        }
        return -1;
    }

    public long getCityPopulation(String city) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT Population FROM city WHERE Name = '" + city + "'");
            if (rs.next()) {
                return rs.getLong("Population");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population of" + city);
        }
        return -1;
    }

    public ArrayList<Population> getLanguageReport() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Language, SUM(Population) AS TotalPopulation " +
                            "FROM countrylanguage cl " +
                            "JOIN country c ON cl.CountryCode = c.Code " +
                            "GROUP BY Language " +
                            "HAVING Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                            "ORDER BY TotalPopulation DESC";
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<Population> languageReport = new ArrayList<>();
            while (rset.next()) {
                Population population = new Population(
                        rset.getString("Language"),
                        rset.getLong("TotalPopulation")
            );
                languageReport.add(population);
            }
            return languageReport;
        }
           catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to get population by languages");
                return null;
            }
    }

}

