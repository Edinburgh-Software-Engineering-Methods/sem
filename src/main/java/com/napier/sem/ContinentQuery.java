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
            String strSelect = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " + "WHERE Continent = '" + continent + "' " + "ORDER BY Population DESC";
            ResultSet rset = stmt.executeQuery(strSelect);
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
            System.out.println("Failed to get countries by region");
            return null;
        }
    }

    public ArrayList<Country> getTopCountriesByContinent(String continent, int topN) {
        try {
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital " +
                            "FROM country " +
                            "WHERE Continent = ? " +
                            "ORDER BY Population DESC " +
                            "LIMIT ?";
            // Prepare the statement
            PreparedStatement pstmt = con.prepareStatement(strSelect);
            pstmt.setString(1, continent);
            pstmt.setInt(2, topN);
            // Execute SQL statement
            ResultSet rset = pstmt.executeQuery();
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
            System.out.println("Failed to get countries by population in continent");
            return null;
        }
    }

    public ArrayList<City> getCitiesByContinent(String continent) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT Name, CountryCode, District, Population " +
                    "FROM city " +
                    "WHERE CountryCode IN (SELECT Code FROM country WHERE Continent = '" + continent + "') " +
                    "ORDER BY Population DESC";
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract city information
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
            System.out.println("Failed to get cities by continent");
            return null;
        }
    }
}



