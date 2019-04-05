package controller;

import Model.Schedule;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Database;

import java.io.IOException;
import java.sql.*;

public class ScheduleController {

    @FXML private TextField courseadd;
    @FXML private Button add,drop,mainpage;
    @FXML
    TableView <Schedule>scheduled;
    @FXML Label warning;
    @FXML
    private TableColumn<Schedule,String> courseid,coursetitle,credit;

    ObservableList<Schedule> sche = FXCollections.observableArrayList();
    User user = new User();

    @FXML public void DropBtn(){
        if(scheduled.getSelectionModel().isEmpty()){
            warning.setText("No row selected");
            warning.setVisible(true);
        }
        else{
            warning.setVisible(false);
            String courseID = scheduled.getSelectionModel().getSelectedItem().getCourseid();
            Database.remove(courseID,user.getId());
            Database.setStatusDisable(courseID);
            add();
        }
    }


    @FXML public void toMainPageBtn(ActionEvent event){
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

    @FXML public void addCourseBtn(){
        if(courseadd.getText().equals("")){
            warning.setVisible(true);
            warning.setText("Please Enter Course ID.");
        }
        else{
            if(!Database.checkCourse(courseadd.getText())){
                warning.setVisible(true);
                warning.setText("No course with this ID.");
            }else {
                if(!Database.checkTake(courseadd.getText())){
                    try{
                        User user = new User();
                        Database.addCourse(courseadd.getText(),user.getId());
                        Database.setStatusEnable(courseadd.getText());
                        warning.setVisible(false);
                    }catch (IllegalArgumentException e){
                        warning.setVisible(true);
                        warning.setText(e.getMessage());
                    }
                }
                else{
                    warning.setVisible(true);
                    warning.setText("You already take this course.");
                }
            }
        }
        courseadd.clear();
        add();
    }

    public void add(){
        sche.clear();
        String driver = "org.sqlite.JDBC";
        String urlDB = "jdbc:sqlite:regis.db";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(urlDB);
            String query = "Select * From Take Where id = '"+user.getId()+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {sche.add(new Schedule(resultSet.getString("course_id"),
                    resultSet.getString("course_title"), resultSet.getString("credit")));
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        warning.setVisible(false);
        courseid.setCellValueFactory(new PropertyValueFactory<Schedule, String>("courseid"));
        courseid.setStyle("-fx-alignment: CENTER;");
        coursetitle.setCellValueFactory(new PropertyValueFactory<Schedule, String>("coursetitle"));
        coursetitle.setStyle("-fx-alignment: CENTER;");
        credit.setCellValueFactory(new PropertyValueFactory<Schedule, String>("credit"));
        credit.setStyle("-fx-alignment: CENTER;");

        scheduled.setItems(sche);
        add();


    }
}
