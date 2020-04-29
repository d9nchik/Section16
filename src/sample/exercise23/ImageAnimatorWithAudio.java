package sample.exercise23;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class ImageAnimatorWithAudio extends Application {

    private final ImageView imageView = new ImageView();
    private Timeline animation;
    private String headerOfPath;
    private int maxNumber;
    private AudioClip audio;
    private int counter;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();


        pane.setCenter(imageView);

        Button startAnimation = new Button("Start Animation");
        pane.setRight(startAnimation);

        GridPane table = new GridPane();
        table.setAlignment(Pos.CENTER_LEFT);
        pane.setBottom(table);
        table.add(new Text("Enter information for animation"), 0, 0);
        table.add(new Text("Path to folder: "), 0, 1);
        TextField folderPath = new TextField();
        table.add(folderPath, 1, 1);
        table.add(new Text("Animation speed in milliseconds"), 0, 2);
        TextField animationSpeed = new TextField();
        table.add(animationSpeed, 1, 2);
        table.add(new Text("Image file prefix"), 0, 3);
        TextField prefix = new TextField();
        table.add(prefix, 1, 3);
        table.add(new Text("Number of images"), 0, 4);
        TextField numberImages = new TextField();
        table.add(numberImages, 1, 4);
        table.add(new Text("Audio file URL"), 0, 5);
        TextField audioURL = new TextField();
        table.add(audioURL, 1, 5);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 23");
        primaryStage.show();


        startAnimation.setOnAction(e -> {
            animation = new Timeline(new KeyFrame(Duration.millis(Integer.parseInt(animationSpeed.getText())), a -> slide()));
            animation.setCycleCount(Timeline.INDEFINITE);
            headerOfPath = folderPath.getText() + prefix.getText();
            maxNumber = Integer.parseInt(numberImages.getText());
            counter = 1;
            audio = new AudioClip(audioURL.getText());
            audio.setCycleCount(AudioClip.INDEFINITE);
            audio.play();
            animation.play();
        });
    }

    private void slide() {
        if (counter <= maxNumber)
            imageView.setImage(new Image(new File(headerOfPath + counter++ + ".gif").toURI().toString()));
        else {
            audio.stop();
            animation.stop();
        }
    }
}
