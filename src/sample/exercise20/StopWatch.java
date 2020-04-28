package sample.exercise20;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StopWatch extends Application {

    private final Text tf = new Text();
    private int hour, minute, seconds;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        tf.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        updateText();
        pane.setCenter(new StackPane(tf));

        HBox buttons = new HBox();
        pane.setBottom(buttons);
        buttons.setSpacing(5);
        buttons.setAlignment(Pos.CENTER);
        Button startPauseResume = new Button("Start");
        Button clear = new Button("Clear");
        buttons.getChildren().addAll(startPauseResume, clear);

        Scene scene = new Scene(pane, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 20");
        primaryStage.show();

        Timeline animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> incrementTime()));
        animation.setCycleCount(Animation.INDEFINITE);

        clear.setOnAction(e -> {
            animation.stop();
            hour = minute = seconds = 0;
            updateText();
            startPauseResume.setText("Start");
        });

        startPauseResume.setOnAction(e -> {
            switch (animation.getStatus()) {
                case RUNNING:
                    animation.pause();
                    startPauseResume.setText("Resume");
                    break;
                case STOPPED:
                case PAUSED:
                    animation.play();
                    startPauseResume.setText("Pause");
                    break;
            }
        });
    }

    private void updateText() {
        tf.setText(String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":"
                + String.format("%02d", seconds));
    }

    private void incrementTime() {
        seconds += 1;
        if (seconds == 60) {
            seconds = 0;
            minute += 1;
            if (minute == 60) {
                minute = 0;
                hour += 1;
            }
        }
        updateText();
    }
}
