package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Label warning;
    @FXML private Button login;

    @FXML public void LoginBtn(ActionEvent actionEvent){
        if(username.getText().equals("") || password.getText().equals("")){
            warning.setText("Please Enter Username and Password");
        }
        else {
            if(DBConnection.login(username.getText(),password.getText())) {
                login = (Button) actionEvent.getSource();
                Stage stage =(Stage)login.getScene().getWindow();
                DBConnection.setUser(username.getText());
                FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/resource/Menu.fxml"));
                try{
                    stage.setScene(new Scene(fxmlLoader.load(),700,500));
                    stage.show();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            else{
                warning.setText("Wrong username or Password");
                password.clear();
            }
        }
    }

    private void loginToHome(Stage stage) throws IOException{
        if(username.getText().equals("") || password.getText().equals("")){
            warning.setText("Please Enter Username and Password");
        }
        else {
            if(DBConnection.login(username.getText(),password.getText())) {
                DBConnection.setUser(username.getText());
                FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/resource/Menu.fxml"));
                try{
                    stage.setScene(new Scene(fxmlLoader.load(),700,500));
                    stage.show();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            else{
                warning.setText("Wrong username or Password");
                password.clear();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        warning.setText("");
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
}
