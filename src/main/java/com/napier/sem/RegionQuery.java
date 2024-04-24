package com.napier.sem;
import java.sql.*;
import java.util.ArrayList;
public class RegionQuery {

    private final Connection con;

    public RegionQuery(Connection con) {
        this.con = con;
    }

    public ArrayList<Country> getCountriesByRegion(String region) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci ON c.Capital = ci.ID " +
                    "WHERE c.Region = '" + region + "' " +
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
            System.out.println("Failed to get countries by" + region);
            return null;
        }
    }

    public ArrayList<Country> getTopCountriesByRegion(String region, int N) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci ON c.Capital = ci.ID " +
                    "WHERE c.Region = '" + region + "' " +
                    "ORDER BY c.Population DESC " +
                    "LIMIT " + N; //
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
            System.out.println("Failed to get countries by population in continent");
            return null;
        }
    }

    public ArrayList<City> getCitiesByRegion(String region) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT ci.Name AS City, c.Name AS Country, ci.District, ci.Population " +
                    "FROM city ci " +
                    "JOIN country c ON ci.CountryCode = c.Code " +
                    "WHERE c.Region = '" + region + "' " +
                    "ORDER BY ci.Population DESC";// Order by population from largest to smallest
            // Execute SQL statement
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
            System.out.println("Failed to get cities by population in" + region);
            return null;
        }
    }


    public ArrayList<City> getTopCitiesByRegion(String region, int N) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT ci.Name AS City, c.Name AS Country, ci.District, ci.Population " +
                    "FROM city ci " +
                    "JOIN country c ON ci.CountryCode = c.Code " +
                    "WHERE c.Region = '" + region + "' " +
                    "ORDER BY ci.Population DESC " +
                    "LIMIT " + N;
            // Execute SQL statement
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
            System.out.println("Failed to get top " + N + " cities by population in " + region);
            return null;
        }
    }

    public ArrayList<Country> getCapitalCitiesByRegion(String region) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT c.Name, c.Population, ci.Name AS Capital " +
                    "FROM country c " +
                    "LEFT JOIN city ci ON c.Capital = ci.ID " +
                    "WHERE c.Region = '" + region + "' " +
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
            System.out.println("Failed to get countries by" + region);
            return null;
        }
    }

    public ArrayList<Country> getTopCapitalCitiesByRegion(String region, int N) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT c.Name, c.Population, ci.Name AS Capital " +
                            "FROM country c " +
                            "LEFT JOIN city ci ON c.Capital = ci.ID " +
                            "WHERE c.Region = '" + region + "' " +
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
            System.out.println("Failed to get top" + N + "cities in " + region);
            return null;
        }
    }

    // Get population data for all regions
    public ArrayList<Population> getPopulationByRegion() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT c.Region, SUM(c.Population) AS TotalPopulation, " +
                    "SUM(ci.Population) AS PopulationInCities, " +
                    "(SUM(c.Population) - SUM(ci.Population)) AS PopulationNotInCities " +
                    "FROM country c " +
                    "LEFT JOIN city ci ON c.Capital = ci.ID " +
                    "GROUP BY c.Region";
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<Population> populationList = new ArrayList<>();
            while (rset.next()) {
                Population population = new Population(
                        rset.getString("Region"),
                        rset.getInt("PopulationInCities"),
                        rset.getInt("PopulationNotInCities"),
                        rset.getInt("TotalPopulation")
                );
                populationList.add(population);
            }
            return populationList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population distribution by regions");
            return null;
        }
    }

    public long getRegionPopulation(String region) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT SUM(Population) AS RegionPopulation FROM country WHERE Region = '" + region + "'");
            if (rs.next()) {
                return rs.getLong("RegionPopulation");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population of" + region);
        }
        return -1;
    }

}
