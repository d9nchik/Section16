package sample.exercise28;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextSlideShow extends Application {

    private int counter = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            pane.getChildren().clear();
            TextArea ta = new TextArea(read(counter));
            ta.setEditable(false);
            pane.getChildren().add(ta);
            counter = (1 + counter) % 9;
        }));
        timeline.setCycleCount(Animation.INDEFINITE);

        Scene scene = new Scene(pane, 150, 50);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 28");
        primaryStage.show();
        timeline.play();
    }

    public String read(int index) {
        try (Scanner input = new Scanner(new BufferedInputStream(
                new FileInputStream("src/sample/exercise28/text/" + index + ".txt")))) {
            StringBuilder stringBuilder = new StringBuilder();
            while (input.hasNext())
                stringBuilder.append(input.nextLine()).append("\n");
            return stringBuilder.toString();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
        return "";
    }
}
