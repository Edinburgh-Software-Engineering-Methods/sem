package com.napier.sem;
import java.sql.*;
import java.util.ArrayList;
public class CountryQuery {

    private final Connection con;

    public CountryQuery(Connection con) {
        this.con = con;
}

    public ArrayList<City> getCitiesByCountry(String countryCode) {
        try {
            PreparedStatement pstmt = con.prepareStatement(
                    "SELECT Name, CountryCode, District, Population " +
                            "FROM city " +
                            "WHERE CountryCode = ? " +
                            "ORDER BY Population DESC");
            pstmt.setString(1, countryCode);
            ResultSet rset = pstmt.executeQuery();
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
            System.out.println("Failed to get cities by country");
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