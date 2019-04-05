package controller;

import Model.CuriculumModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class CuriculumController {
    @FXML
    Button mainpage,show;
    @FXML
    ChoiceBox<String> semester,year;

    @FXML
    TableView<CuriculumModel> curiculum;
    @FXML
    TableColumn<CuriculumModel,String> courseid,coursetitle,credit,prerequisite,term;
    @FXML TableColumn<CuriculumModel,Integer> yeartal;
    @FXML TableColumn<CuriculumModel, Pane> level;
    @FXML TableColumn<CuriculumModel, ImageView> status;

    ObservableList<CuriculumModel> table = FXCollections.observableArrayList();

    public void show(){
        table.clear();
        if(semester.getValue().equals("-") && year.getValue().equals("-")){
            String driver = "org.sqlite.JDBC";
            String urlDB = "jdbc:sqlite:regis.db";

            try {
                Class.forName(driver);
                Connection connection = DriverManager.getConnection(urlDB);
                String query = "Select * From Course";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {table.add(new CuriculumModel(resultSet.getString("course_id"),
                        resultSet.getString("course_title"), resultSet.getString("credit"),
                        resultSet.getString("pre_reposit"), resultSet.getString("term"),
                        resultSet.getInt("year"),resultSet.getInt("level"),resultSet.getInt("status")));
                }
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(!semester.getValue().equals("-") && !year.getValue().equals("-")){
            String driver = "org.sqlite.JDBC";
            String urlDB = "jdbc:sqlite:regis.db";

            try {
                Class.forName(driver);
                Connection connection = DriverManager.getConnection(urlDB);
                String query = "Select * From Course Where term = '"+semester.getValue()+"' and year = '"+year.getValue()+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {table.add(new CuriculumModel(resultSet.getString("course_id"),
                        resultSet.getString("course_title"), resultSet.getString("credit"),
                        resultSet.getString("pre_reposit"), resultSet.getString("term"),
                        resultSet.getInt("year"),resultSet.getInt("level"),resultSet.getInt("status")));
                }
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(semester.getValue().equals("-") && !year.getValue().equals("-")){
            String driver = "org.sqlite.JDBC";
            String urlDB = "jdbc:sqlite:regis.db";

            try {
                Class.forName(driver);
                Connection connection = DriverManager.getConnection(urlDB);
                String query = "Select * From Course Where year  = '"+year.getValue()+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {table.add(new CuriculumModel(resultSet.getString("course_id"),
                        resultSet.getString("course_title"), resultSet.getString("credit"),
                        resultSet.getString("pre_reposit"), resultSet.getString("term"),
                        resultSet.getInt("year"),resultSet.getInt("level"),resultSet.getInt("status")));
                }
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            String driver = "org.sqlite.JDBC";
            String urlDB = "jdbc:sqlite:regis.db";

            try {
                Class.forName(driver);
                Connection connection = DriverManager.getConnection(urlDB);
                String query = "Select * From Course Where term = '"+semester.getValue()+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {table.add(new CuriculumModel(resultSet.getString("course_id"),
                        resultSet.getString("course_title"), resultSet.getString("credit"),
                        resultSet.getString("pre_reposit"), resultSet.getString("term"),
                        resultSet.getInt("year"),resultSet.getInt("level"),resultSet.getInt("status")));
                }
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void toMainPageBtn(ActionEvent event){
        mainpage = (Button) event.getSource();
        Stage stage = (Stage) mainpage.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resource/MainPage.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 700, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML public void showBtn(){
        show();
    }
    public void initialize(){
        semester.getItems().add("-");
        semester.getItems().add("First");
        semester.getItems().add("Second");
        semester.setValue("-");
        semester.setStyle("-fx-alignment: CENTER;");
        year.getItems().add("-");
        year.getItems().add("1");
        year.getItems().add("2");
        year.getItems().add("3");
        year.getItems().add("4");
        year.setValue("-");
        year.setStyle("-fx-alignment: CENTER;");

        courseid.setCellValueFactory(new PropertyValueFactory<CuriculumModel, String>("courseid"));
        courseid.setStyle("-fx-alignment: CENTER;");
        coursetitle.setCellValueFactory(new PropertyValueFactory<CuriculumModel, String>("coursetitle"));
        coursetitle.setStyle("-fx-alignment: CENTER;");
        credit.setCellValueFactory(new PropertyValueFactory<CuriculumModel, String>("credit"));
        credit.setStyle("-fx-alignment: CENTER;");
        prerequisite.setCellValueFactory(new PropertyValueFactory<CuriculumModel, String>("prerequirsite"));
        prerequisite.setStyle("-fx-alignment: CENTER;");
        term.setCellValueFactory(new PropertyValueFactory<CuriculumModel, String>("term"));
        term.setStyle("-fx-alignment: CENTER;");
        yeartal.setCellValueFactory(new PropertyValueFactory<CuriculumModel, Integer>("year"));
        yeartal.setStyle("-fx-alignment: CENTER;");
        level.setCellValueFactory(new PropertyValueFactory<CuriculumModel, Pane>("level"));
        level.setStyle("-fx-alignment: CENTER;");
        status.setCellValueFactory(new PropertyValueFactory<CuriculumModel, ImageView>("status"));
        status.setStyle("-fx-alignment: CENTER;");

        curiculum.setItems(table);
        show();
    }

}
