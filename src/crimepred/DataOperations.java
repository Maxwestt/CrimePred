/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crimepred;

/**
 *
 * @author Chris
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DataOperations {

    public static void main(String[] args) {
        //insertData();
        // System.out.println(getData(new Date(System.currentTimeMillis())).toString());
    }

    public static ArrayList<Crime> getCrimes(Date date, Boolean day) {

        ArrayList<Crime> crimeList = new ArrayList<>();
        java.sql.Date begin = new java.sql.Date(date.getTime() - Long.parseLong("31536000000"));
        java.sql.Date end = new java.sql.Date(date.getTime() - Long.parseLong("86400000"));
        java.sql.Date dayDate = new java.sql.Date(date.getTime());

        String sql = "";
        if (day == false) {
            sql = "SELECT * From CRIMEDATA where ARREST_DATE BETWEEN ? AND ?";
        } else {
            sql = "SELECT * From CRIMEDATA where ARREST_DATE = ?";
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/LACrime");
            PreparedStatement statement = connection.prepareStatement(sql);
            if (day == false) {
                statement.setDate(1, begin);
                statement.setDate(2, end);
            } else {
                statement.setDate(1, dayDate);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                crimeList.add(new Crime(resultSet.getDate("ARREST_DATE"), resultSet.getTime("TIME"),
                        resultSet.getInt("REPORTING_DISTRICT"), resultSet.getInt("AGE"), resultSet.getString("SEX_CODE"),
                        resultSet.getString("DESCENT_CODE"), resultSet.getInt("CHARGE_GROUP_CODE"), resultSet.getString("ADDRESS"),
                        resultSet.getString("CROSS_STREET"), resultSet.getDouble("LATITUDE"), resultSet.getDouble("LONGITUDE")));
            }
            connection.close();
            statement.close();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crimeList;
    }

    public static void insertData() {
        Connection connection = null;
        try {
            //Change Path here
            String csvFilePath = "C:\\Users\\Chris\\Documents\\Hochschule\\Auslandssemester\\Fächer\\AdvancedDatabaseSystems\\crimeData.csv";
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/LACrime");
            String sql = "INSERT INTO crimedata (arrest_date, time, reporting_district, age, sex_code, descent_code, charge_group_code, address, cross_street, latitude, longitude) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;
            lineReader.readLine(); // skip header line
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(";");
                if ("Burglary".equals(data[10]) || "Robbery".equals(data[10])) {
                    //Arrest_Date
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date = dateFormat.parse(data[1].substring(0, 9));
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    statement.setDate(1, sqlDate);
                    //Time
                    if (data[2].length() <= 2) {
                        data[2] += "00";
                    }
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
                    java.util.Date time = timeFormat.parse(data[2]);
                    java.sql.Time sqlTime = new java.sql.Time(time.getTime());
                    statement.setTime(2, sqlTime);
                    //Reporting_District
                    statement.setInt(3, Integer.parseInt(data[5]));
                    //Age
                    statement.setInt(4, Integer.parseInt(data[6]));
                    //Sex_Code
                    statement.setString(5, data[7]);
                    //Descent_Code
                    statement.setString(6, data[8]);
                    //Charge_Group_Code
                    statement.setInt(7, Integer.parseInt(data[9]));
                    //Address
                    statement.setString(8, data[14]);
                    //Cross_Street
                    statement.setString(9, data[15]);
                    //Coordinate
                    String[] location = data[16].split(",");
                    //Latitude
                    statement.setDouble(10, Double.parseDouble(location[0].replaceAll("[^0-9.]", "")));
                    //Longitude
                    statement.setDouble(11, Double.parseDouble(location[location.length - 1].replaceAll("[^-0-9.]", "")));
                    //Insert
                    statement.executeUpdate();
                }
            }

            connection.close();
            statement.close();
            lineReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);

        }
    }
}
