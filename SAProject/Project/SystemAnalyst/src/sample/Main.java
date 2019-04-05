package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static Stage pristage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        pristage = primaryStage;
        Parent root = FXMLLoader.load(Main.class.getResource("../resource/sample.fxml"));
        primaryStage.setTitle("Medical Tools");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

    public static void changeScene(String scenename) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("../resource/"+scenename));
        pristage.setScene(new Scene(root, 700, 500));
        pristage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
