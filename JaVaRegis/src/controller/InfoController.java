package controller;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;


public class InfoController {
    @FXML
    Label name,id;
    @FXML
    Button mainpage;
    @FXML
    ImageView image;

    User user = new User();

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

    public void initialize(){
        name.setText("Name : "+user.getName());
        id.setText("ID : "+user.getId());
        if(user.getId().equals("5910450409")){
            Image im = new Image("/image/bom.jpg");
            image.setImage(im);
        }
        else{
            Image im = new Image("/image/phat.jpg");
            image.setImage(im);
        }
    }
}
