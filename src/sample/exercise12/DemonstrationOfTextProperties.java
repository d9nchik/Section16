package sample.exercise12;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DemonstrationOfTextProperties extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(false);
        try (Scanner input = new Scanner(new BufferedInputStream(
                new FileInputStream("src/sample/exercise12/input.txt")))) {
            while (input.hasNext())
                textArea.setText(textArea.getText() + "\n" + input.nextLine());
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        pane.setCenter(textArea);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        CheckBox editable = new CheckBox("Editable");
        CheckBox wrap = new CheckBox("Wrap");
        hBox.getChildren().addAll(editable, wrap);
        pane.setBottom(hBox);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("exercise 12");
        primaryStage.show();

        editable.setOnAction(e -> textArea.setEditable(editable.isSelected()));
        wrap.setOnAction(e -> textArea.setWrapText(wrap.isSelected()));
    }
}
