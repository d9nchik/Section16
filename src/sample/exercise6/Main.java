package sample.exercise6;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);

        TextField textField = new TextField("JavaFX");
        Label label = new Label("Text Field", textField);
        label.setContentDisplay(ContentDisplay.RIGHT);
        label.setLineSpacing(10);
        pane.getChildren().add(label);

        HBox buttons = new HBox();
        pane.getChildren().add(buttons);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);

        ToggleGroup positionOfTextField = new ToggleGroup();
        RadioButton left = new RadioButton("Left");
        left.setToggleGroup(positionOfTextField);
        RadioButton center = new RadioButton("Center");
        center.setToggleGroup(positionOfTextField);
        RadioButton right = new RadioButton("Right");
        right.setToggleGroup(positionOfTextField);
        buttons.getChildren().addAll(left, center, right);

        TextField textFieldSize = new TextField();
        textFieldSize.setPrefColumnCount(3);
        Label columnSize = new Label("Column Size", textFieldSize);
        columnSize.setContentDisplay(ContentDisplay.RIGHT);
        buttons.getChildren().add(columnSize);


        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 6");
        primaryStage.show();

        left.setOnAction(e -> textField.setAlignment(Pos.CENTER_LEFT));
        center.setOnAction(e -> textField.setAlignment(Pos.CENTER));
        right.setOnAction(e -> textField.setAlignment(Pos.CENTER_RIGHT));
        textFieldSize.setOnAction(e -> textField.setPrefColumnCount(Integer.parseInt(textFieldSize.getText())));
    }
}
