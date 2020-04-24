package sample.exercise3;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        final trafficLights trafficLights = new trafficLights();
        pane.setCenter(trafficLights);

        ToggleGroup colours = new ToggleGroup();
        RadioButton red = new RadioButton("Red");
        RadioButton yellow = new RadioButton("Yellow");
        RadioButton green = new RadioButton("Green");
        red.setToggleGroup(colours);
        yellow.setToggleGroup(colours);
        green.setToggleGroup(colours);
        HBox buttons = new HBox(red, yellow, green);
        buttons.setSpacing(5);
        buttons.setAlignment(Pos.CENTER);
        pane.setBottom(buttons);

        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("Exercise 3");
        primaryStage.setScene(scene);
        primaryStage.show();

        red.setOnAction(e -> trafficLights.turnOnRed());
        yellow.setOnAction(e -> trafficLights.turnOnYellow());
        green.setOnAction(e -> trafficLights.turnOnGreen());
    }
}

class trafficLights extends StackPane {
    private final Group pane = new Group();

    public trafficLights() {
        for (int i = 0; i < 3; i++) {
            Circle circle = new Circle(-50 + (50) * i, 0, 20);
            circle.setStroke(Color.BLACK);
            circle.setFill(null);
            pane.getChildren().add(circle);
        }
        Rectangle rectangle = new Rectangle(-80, -30, 160, 60);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(null);
        pane.getChildren().add(rectangle);
        getChildren().add(pane);
    }

    private void turnOfLight() {
        for (int i = 0; i < 3; i++)
            if (pane.getChildren().get(i) instanceof Shape)
                ((Shape) pane.getChildren().get(i)).setFill(null);
    }

    public void turnOnRed() {
        turnOfLight();
        if (pane.getChildren().get(0) instanceof Shape)
            ((Shape) pane.getChildren().get(0)).setFill(Color.RED);
    }

    public void turnOnYellow() {
        turnOfLight();
        if (pane.getChildren().get(1) instanceof Shape)
            ((Shape) pane.getChildren().get(1)).setFill(Color.YELLOW);
    }

    public void turnOnGreen() {
        turnOfLight();
        if (pane.getChildren().get(2) instanceof Shape)
            ((Shape) pane.getChildren().get(2)).setFill(Color.GREEN);
    }
}
