package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(Main.class.getResource("../resource/sample.fxml"));
        primaryStage.setTitle("Alarm Clock");
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.show();
    }

    public static void changeScene(String scenename) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(scenename));
        stage.setScene(new Scene(root, 300, 400));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
