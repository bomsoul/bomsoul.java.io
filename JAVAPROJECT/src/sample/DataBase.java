package sample;


import java.sql.*;

public class DataBase {
    private static String driver = "org.sqlite.JDBC";
    private static String urlDB = "jdbc:sqlite:alarmclock.db";

    public static void add(String time){
        String [] times = time.split(":");
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "INSERT INTO Time VALUES('"+times[0]+"','"+times[1]+"',0)";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void remove(String time){
        String [] times = time.split(":");
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Delete From Time Where hour ='"+times[0]+"'and minute ='"+times[1]+"'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void setStatus(String time){
        String [] times = time.split(":");
        int status = times[2].charAt(0);
        if( status == '0')
            status = 1;
        else{
            status = 0;
        }
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "UPDATE Time SET status = "+status+ " WHERE hour ='"+times[0]+"' and minute ='"+times[1]+"'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void status(String time){
        String [] times = time.split(":");
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select * From Time Where hour = '"+times[0]+"' and minute = '"+times[1]+"'";
            Statement statement = connection.createStatement();
            ResultSet Rs = statement.executeQuery(query);
            String t = Rs.getString("hour") +":"+Rs.getString("minute")+":"+Rs.getInt("status");
            connection.close();
            setStatus(t);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checktime(String time){
        String [] times = time.split(":");
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "SELECT * FROM Time Where hour ='"+times[0]+"' and minute ='"+times[1]+"'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            connection.close();
            while(rs.next())
                return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean Alarm(String time){
        String [] times = time.split(":");
        boolean check = false;
        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "SELECT * FROM Time";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                if(times[0].equals(rs.getString("hour")) && times[1].equals(rs.getString("minute"))
                && rs.getInt("status") == 1){
                    check = true;
                    break;
                }
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

}
