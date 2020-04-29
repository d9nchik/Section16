package sample.exercise22;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.File;

public class SoundClip extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AudioClip sound = new AudioClip(new File("src/sounds/karantin.mp3").toURI().toString());


        Button play = new Button("Play");
        Button loop = new Button("Loop");
        Button stop = new Button("Stop");

        HBox buttons = new HBox(play, loop, stop);
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(10, 10, 10, 10));


        Scene scene = new Scene(buttons);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 22");
        primaryStage.show();

        play.setOnAction(e -> {
            sound.setCycleCount(1);
            sound.play();
        });

        loop.setOnAction(e -> {
            sound.setCycleCount(AudioClip.INDEFINITE);
            sound.play();
        });

        stop.setOnAction(e -> sound.stop());
    }
}
