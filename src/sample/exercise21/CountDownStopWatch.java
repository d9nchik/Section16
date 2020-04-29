package sample.exercise21;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class CountDownStopWatch extends Application {

    private final MediaPlayer player = new MediaPlayer(new Media(new File("src/sounds/alarm.mp3")
            .toURI().toString()));
    private Timeline animation;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        TextField counterText = new TextField("30");
        counterText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));

        Scene scene = new Scene(new StackPane(counterText), 50, 50);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 21");
        primaryStage.show();

        animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> decrementTime(counterText)));
        animation.setCycleCount(Animation.INDEFINITE);

        player.setCycleCount(MediaPlayer.INDEFINITE);

        counterText.setOnAction(e -> {
            animation.play();
            player.stop();
        });
    }

    private void decrementTime(TextField textField) {
        int number = Integer.parseInt(textField.getText());
        if (number > 0)
            textField.setText("" + (number - 1));
        else {
            animation.stop();
            player.play();
        }
    }
}
