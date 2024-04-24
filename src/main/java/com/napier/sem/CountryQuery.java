package com.napier.sem;
import java.sql.*;
import java.util.ArrayList;
public class CountryQuery {

    private final Connection con;

    public CountryQuery(Connection con) {
        this.con = con;
}

    public ArrayList<City> getCitiesByCountry(String country) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT ci.Name AS City, c.Name AS Country, ci.District, ci.Population " +
                    "FROM city ci " +
                    "JOIN country c ON ci.CountryCode = c.Code " +
                    "WHERE c.Name = '" + country + "' " +
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
            System.out.println("Failed to get cities by population in" + country);
            return null;
        }
    }

    public ArrayList<City> getTopCitiesByCountry(String country, int N) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT ci.Name AS City, c.Name AS Country, ci.District, ci.Population " +
                    "FROM city ci " +
                    "JOIN country c ON ci.CountryCode = c.Code " +
                    "WHERE c.Name = '" + country + "' " +
                    "ORDER BY ci.Population DESC " +
                    "LIMIT " + N;
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
            System.out.println("Failed to get top " + N + " cities by population in " + country);
            return null;
        }
    }

    // Get population data for all countries
    public ArrayList<Population> getPopulationByCountry() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT c.Name, SUM(c.Population) AS TotalPopulation, " +
                    "SUM(ci.Population) AS PopulationInCities, " +
                    "(SUM(c.Population) - SUM(ci.Population)) AS PopulationNotInCities " +
                    "FROM country c " +
                    "LEFT JOIN city ci ON c.Capital = ci.ID " +
                    "GROUP BY c.Name";
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<Population> populationList = new ArrayList<>();
            while (rset.next()) {
                Population population = new Population(
                        rset.getString("Name"),
                        rset.getInt("PopulationInCities"),
                        rset.getInt("PopulationNotInCities"),
                        rset.getInt("TotalPopulation")
                );
                populationList.add(population);
            }
            return populationList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population distribution by countries");
            return null;
        }
    }

    public long getCountryPopulation(String country) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT Population FROM country WHERE Name = '" + country + "'");
            if (rs.next()) {
                return rs.getLong("Population");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population of" + country);
        }
        return -1;
    }
}