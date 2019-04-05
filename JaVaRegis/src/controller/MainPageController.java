package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {
    @FXML
    Button logout,curiculum,schedule,Grade;
    @FXML
    Hyperlink info;

    @FXML public void toCuriculumPageBtn(ActionEvent event){
        curiculum = (Button) event.getSource();
        Stage stage = (Stage) curiculum.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resource/curiculum.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 700, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void toNisitInfoLink(ActionEvent event){
        info = (Hyperlink) event.getSource();
        Stage stage = (Stage) info.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resource/Info.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 700, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void toCalculateGradeBtn(ActionEvent event){
        Grade = (Button) event.getSource();
        Stage stage = (Stage) Grade.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resource/Calculate.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 700, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void toScheduleBtn(ActionEvent event){
        schedule = (Button) event.getSource();
        Stage stage = (Stage) schedule.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resource/Schedule.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 700, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void toLoginPageBtn(ActionEvent event){
        curiculum = (Button) event.getSource();
        Stage stage = (Stage) curiculum.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resource/sample.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 700, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
