package sample.exercise11;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HistogramForOccurrences extends Application {
    private int[] counterOfLetter;

    public static void main(String[] args) {
        launch(args);
    }

    private static int max(int[] numbers) {
        int maximum = numbers[0];
        for (int number : numbers)
            if (maximum < number)
                maximum = number;
        return maximum;
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        HBox diagrams = new HBox();
        diagrams.setAlignment(Pos.BOTTOM_CENTER);
        pane.setCenter(diagrams);

        HBox hBox = new HBox();
        hBox.setSpacing(5);
        TextField textField = new TextField();
        textField.prefWidthProperty().bind(pane.widthProperty().subtract(50));
        Button view = new Button("View");
        hBox.getChildren().addAll(textField, view);
        pane.setBottom(hBox);

        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 11");
        primaryStage.show();

        view.setOnAction(e -> {
            diagrams.getChildren().clear();
            try (Scanner input = new Scanner(new File(textField.getText()))) {
                counterOfLetter = new int['Z' - 'A' + 1];
                while (input.hasNext()) {
                    String line = input.nextLine();
                    for (int i = 0; i < line.length(); i++)
                        if (line.charAt(i) <= 'Z' && line.charAt(i) >= 'A')
                            counterOfLetter[line.charAt(i) - 'A']++;
                        else if (line.charAt(i) <= 'z' && line.charAt(i) >= 'a')
                            counterOfLetter[line.charAt(i) - 'a']++;
                }
                int maximum = max(counterOfLetter);
                for (int k = 0; k < counterOfLetter.length; k++) {
                    Rectangle rectangle = new Rectangle(0, 0, 10, counterOfLetter[k] * 250.0 / maximum);
                    rectangle.setFill(null);
                    rectangle.setStroke(Color.BLACK);
                    Label label = new Label(((char) ('A' + k)) + "", rectangle);
                    label.setContentDisplay(ContentDisplay.TOP);
                    diagrams.getChildren().add(label);
                }
            } catch (FileNotFoundException ex) {
                System.out.println("File not found!");
            }
        });
    }
}
