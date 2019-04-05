package Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import Model.AlarmTable;
import sample.DataBase;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AlarmController implements Initializable {

    private int second,minute,hours;
    @FXML private Button back,setAlarm,remove,alarm;
    @FXML private Label clock,warning;
    @FXML private ChoiceBox<String> hour,minut;

    @FXML private TableView<AlarmTable> Alarm;
    @FXML private TableColumn<AlarmTable,String> h;
    @FXML private TableColumn<AlarmTable, String> m;
    @FXML private TableColumn<AlarmTable, Image> alert;
    ObservableList<AlarmTable> data = FXCollections.observableArrayList();
    ObservableList<String> timehour = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09",
            "10","11","12","13","14","15","16","17","18","19","20","21","22","23");
    ObservableList<String> timeminute = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09",
            "10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29",
            "30","31","32","33","34","35","36","37","38","39",
            "40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59");

    public void defaultChoiceBox(){
        hour.getItems().addAll(timehour);
        minut.getItems().addAll(timeminute);
    }

    public void show(){
        data.clear();
        try{
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:alarmclock.db");
            String query = "SELECT * FROM Time";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                data.add(new AlarmTable(rs.getString("hour"), rs.getString("minute"), rs.getInt("status")));
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void backBtn(ActionEvent action){
        back = (Button) action.getSource();
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resource/sample.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 300, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBtn(){
        if(!hour.getValue().equals("-") && !minut.getValue().equals("-")){
            String time = hour.getValue()+":"+minut.getValue();
            if(!DataBase.checktime(time)){
                DataBase.add(time);
                show();
            }
        }
        else{
            warning.setText("Please Select hour and minute.");
            warning.setVisible(true);
        }
    }

    public void removeBtn(){
        if(!Alarm.getSelectionModel().isEmpty()){
            String Time = Alarm.getSelectionModel().getSelectedItem().getHour();
            Time += ":"+ Alarm.getSelectionModel().getSelectedItem().getMinute();
            DataBase.remove(Time);
            show();
        }
        else{
            warning.setText("Please select row to remove alarm.");
            warning.setVisible(true);
        }
    }

    public void alarmBtn(){
        if(!Alarm.getSelectionModel().isEmpty()){
            String Time = Alarm.getSelectionModel().getSelectedItem().getHour();
            Time += ":"+ Alarm.getSelectionModel().getSelectedItem().getMinute();
            Time += ":" +Alarm.getSelectionModel().getSelectedItem().getCheck();
            DataBase.setStatus(Time);
            show();
        }
        else{
            warning.setText("Please select row to alarm.");
            warning.setVisible(true);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            second = LocalDateTime.now().getSecond();
            minute = LocalDateTime.now().getMinute();
            hours = LocalDateTime.now().getHour();
            clock.setText(String.format("%02d:%02d:%02d",hours,minute,second));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
        defaultChoiceBox();
        h.setCellValueFactory(new PropertyValueFactory<AlarmTable, String>("hour"));
        h.setStyle("-fx-alignment: CENTER;");
        m.setCellValueFactory(new PropertyValueFactory<AlarmTable, String>("minute"));
        m.setStyle("-fx-alignment: CENTER;");
        alert.setCellValueFactory(new PropertyValueFactory<AlarmTable, Image>("alarm"));
        alert.setStyle("-fx-alignment: CENTER;");
        Alarm.setItems(data);
        warning.setVisible(false);
        warning.setStyle("-fx-alignment: CENTER;");
        hour.setValue("-");
        minut.setValue("-");
        show();
    }
}
