package sample.exercise18;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SingleFan extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new ControlFan());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 18");
        primaryStage.show();
    }
}
