package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import sample.DataBase;
import Model.Date;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FormulationController implements Initializable {

    String [] problem = new String[]{"((9-5)*2)+(7*2)","92-((15*2)+4)","(180+95)-4","(95*3)-(25*10)","((4*6)-3)/7"};
    String [] solution = new String[]{"22","58","271","35","3"};
    int index ;
    @FXML private TextField answer;
    @FXML private Label question,warning;
    @FXML private Button toHome;
    private AudioClip audioClip;

    @FXML
    public void AnswerBtn(ActionEvent action){
        if(answer.getText().equals(solution[index])){
            audioClip.stop();
            Date date = Date.getInstance();
            warning.setVisible(false);
            DataBase.status(String.format("%02d:%02d", date.getHour(),date.getMinute()));
            toHome = (Button) action.getSource();
            Stage stage = (Stage) toHome.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resource/sample.fxml"));
            try {
                stage.setScene(new Scene(fxmlLoader.load(), 300, 400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            warning.setVisible(true);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        index = (int)(Math.random() * 5);
        question.setStyle("-fx-alignment: CENTER;");
        question.setText(problem[index]);
        warning.setVisible(false);
    }

}
