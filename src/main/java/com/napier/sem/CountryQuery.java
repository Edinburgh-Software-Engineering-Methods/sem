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
                City cityData = new City();
                cityData.name = rset.getString("City");
                cityData.country = rset.getString("Country");
                cityData.district = rset.getString("District");
                cityData.population = rset.getInt("Population");
                cities.add(cityData);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities by population in" + country);
            return null;
        }
    }

    public ArrayList<City> getTopCitiesByCountry(String countryCode, int N) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT Name, CountryCode, District, Population " +
                            "FROM city " +
                            "WHERE CountryCode = '" + countryCode + "' " +
                            "ORDER BY Population DESC " +
                            "LIMIT " + N;
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<>();
            while (rset.next()) {
                City cityData = new City();
                cityData.name = rset.getString("Name");
                cityData.country = rset.getString("CountryCode");
                cityData.district = rset.getString("District");
                cityData.population = rset.getInt("Population");
                cities.add(cityData);
            }
            return cities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top " + N + " cities by population in " + countryCode);
            return null;
        }
    }
}