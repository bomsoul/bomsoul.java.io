package Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.DataBase;
import Model.Date;
import sample.Main;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Controller  implements Initializable{
    @FXML
    Label Time;
    private int second,minute,hours;
    @FXML
    Button alarm;
    boolean numset = false;


    @FXML public void AlarmsetBtn(ActionEvent action){
        alarm = (Button) action.getSource();
        Stage stage = (Stage) alarm.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resource/Alarm.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(), 300, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            second = LocalDateTime.now().getSecond();
            minute = LocalDateTime.now().getMinute();
            hours = LocalDateTime.now().getHour();
            Time.setText(String.format("%02d:%02d:%02d",hours,minute,second));
            if(DataBase.Alarm(String.format("%02d:%02d",hours,minute))){
                if(numset == false){
                    numset = true;
                    Date date = Date.getInstance();
                    date.setHour(hours);
                    date.setMinute(minute);
                    try {
                        Main.changeScene("/resource/Formulation.fxml");

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            else{
                numset = false;
            }
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
