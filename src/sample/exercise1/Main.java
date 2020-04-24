package sample.exercise1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        Button left = new Button("<=");
        Button right = new Button("=>");
        final HBox buttons = new HBox(left, right);
        buttons.setSpacing(5);
        buttons.setAlignment(Pos.CENTER);
        pane.setBottom(buttons);

        ToggleGroup colourOfText = new ToggleGroup();
        RadioButton red = new RadioButton("Red");
        red.setToggleGroup(colourOfText);
        RadioButton yellow = new RadioButton("Yellow");
        yellow.setToggleGroup(colourOfText);
        RadioButton black = new RadioButton("Black");
        black.setToggleGroup(colourOfText);
        RadioButton orange = new RadioButton("Orange");
        orange.setToggleGroup(colourOfText);
        RadioButton green = new RadioButton("Green");
        green.setToggleGroup(colourOfText);
        black.setSelected(true);
        final HBox rb = new HBox(red, yellow, black, orange, green);
        rb.setSpacing(5);
        rb.setAlignment(Pos.CENTER);
        pane.setTop(rb);

        Text text = new Text(20, 50, "Welcome to Java");
        Font font = Font.font("Times New Roman", FontWeight.BOLD, 20);
        text.setFont(font);
        final Pane node = new Pane(text);
        node.setStyle("-fx-border-color: blue");
        pane.setCenter(node);

        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 1");
        primaryStage.show();

        left.setOnAction(e -> text.setX(text.getX() - 5));
        right.setOnAction(e -> text.setX(text.getX() + 5));

        red.setOnAction(e -> text.setFill(Color.RED));
        yellow.setOnAction(e -> text.setFill(Color.YELLOW));
        black.setOnAction(e -> text.setFill(Color.BLACK));
        orange.setOnAction(e -> text.setFill(Color.ORANGE));
        green.setOnAction(e -> text.setFill(Color.GREEN));
    }
}
