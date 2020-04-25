package sample.exercise10;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextViewer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        pane.setCenter(textArea);

        HBox hBox = new HBox();
        hBox.setSpacing(5);
        TextField textField = new TextField();
        textField.prefWidthProperty().bind(pane.widthProperty().subtract(50));
        Button view = new Button("View");
        hBox.getChildren().addAll(textField, view);
        pane.setBottom(hBox);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 10");
        primaryStage.show();

        view.setOnAction(e -> {
            try (Scanner input = new Scanner(new File(textField.getText()))) {
                while (input.hasNext())
                    textArea.setText(textArea.getText() + "\n" + input.nextLine());
            } catch (FileNotFoundException ex) {
                System.out.println("File not found!");
            }
        });
    }
}
