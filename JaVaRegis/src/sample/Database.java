package sample;

import java.sql.*;
import java.util.Arrays;

public class Database {
    private static String driver = "org.sqlite.JDBC";
    private static String urlDB = "jdbc:sqlite:regis.db";

    public static boolean checkCourse(String id){
        boolean check = false;
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select course_id From Course Where course_id = '" + id +"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            connection.close();
            if (resultSet.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addCourse(String id,String nisitid){
        String [] prerep = prerep(id,nisitid).split(":");
        if(prerep[0].equals("-")){
            try {
                Class.forName(driver);
                Connection connection = DriverManager.getConnection(urlDB);
                String query = "INSERT INTO Take VALUES('"+prerep[1]+"','"+prerep[2]+"','"+prerep[3]+"','"+nisitid+"')";
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(prerep[0].split(",").length > 1){
            if(allprecourse(prerep[0].split(","),nisitid)){
                try {
                    Class.forName(driver);
                    Connection connection = DriverManager.getConnection(urlDB);
                    String query = "INSERT INTO Take VALUES('"+prerep[1]+"','"+prerep[2]+"','"+prerep[3]+"','"+nisitid+"')";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(query);
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                String[] s = prerep[0].split(",");
                String p = "You must Pass \n";
                for(int i = 0;i < s.length;i++){
                    p+= s[i] +"\n";
                }
                throw new IllegalArgumentException(p);
            }
        }
        else{
            if(oneprecourse(prerep[0],nisitid)){
                try {
                    Class.forName(driver);
                    Connection connection = DriverManager.getConnection(urlDB);
                    String query = "INSERT INTO Take VALUES('"+prerep[1]+"','"+prerep[2]+"','"+prerep[3]+"','"+nisitid+"')";;
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(query);
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                throw new IllegalArgumentException("You must pass "+prerep[0]);
            }
        }
    }

    private static String prerep(String id,String userid){
        String pre ="";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select * From Course Where course_id = '"+ id+ "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            pre = resultSet.getString("pre_reposit")+":"+resultSet.getString("course_id")+":"+resultSet.getString("course_title")+":"+resultSet.getString("credit");
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pre;
    }

    public static void remove(String id,String userid){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Delete From Take Where course_id = '"+ id+ "' and id = '"+userid +"'";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static boolean allprecourse(String [] precourse,String id){
        int count =0;
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select course_id From Take Where id = '"+ id+ "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                for(int i = 0;i < precourse.length;i++){
                    if(resultSet.getString("course_id").equals(precourse[i])){
                        count++;
                    }
                }
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(count == precourse.length)
            return true;
        return false;
    }

    public static boolean oneprecourse(String id,String userid){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select course_id From Take Where course_id = '"+ id + "' and id = '"+userid+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            connection.close();
            if (resultSet.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean login(String username,String password){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select * From User Where username = '" + username +"' and password = '"+ password+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            connection.close();
            if (resultSet.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String userinfo(String username){
        String s = "";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select * From User Where username = '"+ username + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                s +=resultSet.getString("name") +":"+ resultSet.getString("id");
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String search(String id){
        String s = "";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Course WHERE course_id ='"+id+"'";
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                s = resultSet.getString("course_id") + ":" + resultSet.getString("course_title") + ":" + resultSet.getString("credit");
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return s;
    }
    public static void setStatusEnable(String id){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Update Course Set status = 1 Where course_id = '"+ id +"'";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setStatusDisable(String id){
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Update Course Set status = 0 Where course_id = '"+ id +"'";
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkTake(String id){
        boolean check = false;
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select course_id From Take Where course_id = '" + id +"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            connection.close();
            if (resultSet.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
