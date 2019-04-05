package controller;

import Model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.Database;

import java.io.IOException;

public class Controller {
    @FXML
    Button login;
    @FXML
    TextField username,password;
    @FXML
    Label warning;

    @FXML public void toMainPageBtn(ActionEvent action){
        if(username.getText().equals("") || password.getText().equals("")){
            warning.setText("Please input username and Password.");
            warning.setVisible(true);
        }
        else if(Database.login(username.getText(),password.getText())){
            User user = new User();
            String []s = Database.userinfo(username.getText()).split(":");
            user.setName(s[0]);
            user.setId(s[1]);
            login = (Button) action.getSource();
            Stage stage = (Stage) login.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resource/MainPage.fxml"));
            try {
                stage.setScene(new Scene(fxmlLoader.load(), 700, 500));
                stage.show();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            warning.setText("username or password is wrong.");
            warning.setVisible(true);
        }
    }

    public void initialize(){
        warning.setVisible(false);
        username.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    TextField userName = (TextField) event.getSource();
                    Stage stage = (Stage) userName.getScene().getWindow();
                    try {
                        loginToHome(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        password.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    PasswordField passwordField = (PasswordField) event.getSource();
                    Stage stage = (Stage) passwordField.getScene().getWindow();
                    try {
                        loginToHome(stage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void loginToHome(Stage stage) throws IOException{
        if(username.getText().equals("") || password.getText().equals("")){
            warning.setText("Please input username and Password.");
            warning.setVisible(true);
        }
        else if(Database.login(username.getText(),password.getText())){
            User user = new User();
            String []s = Database.userinfo(username.getText()).split(":");
            user.setName(s[0]);
            user.setId(s[1]);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resource/MainPage.fxml"));
            try {
                stage.setScene(new Scene(fxmlLoader.load(), 700, 500));
                stage.show();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            warning.setText("username or password is wrong.");
            warning.setVisible(true);
        }
        username.clear();
    }

}
