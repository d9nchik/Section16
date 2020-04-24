package sample.exercise2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
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

        ToggleGroup figures = new ToggleGroup();
        Button circle = new Button("Circle");
        Button square = new Button("Square");
        Button ellipse = new Button("Ellipse");
        CheckBox isFilled = new CheckBox("Fill");
        HBox buttons = new HBox(circle, square, ellipse, isFilled);
        buttons.setStyle("-fx-border-color: black");
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(5);
        pane.setBottom(buttons);

        StackPane stackPane = new StackPane();
        pane.setCenter(stackPane);

        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 2");
        primaryStage.show();

        circle.setOnAction(e -> {
            stackPane.getChildren().clear();
            Circle circle1 = new Circle(0, 0, 50);
            circle1.setStroke(Color.BLACK);
            if (!isFilled.isSelected())
                circle1.setFill(null);
            stackPane.getChildren().add(circle1);
        });
        square.setOnAction(e -> {
            stackPane.getChildren().clear();
            Rectangle rectangle = new Rectangle(-50, -50, 100, 100);
            rectangle.setStroke(Color.BLACK);
            if (!isFilled.isSelected())
                rectangle.setFill(null);
            stackPane.getChildren().add(rectangle);
        });
        ellipse.setOnAction(e -> {
            stackPane.getChildren().clear();
            Ellipse ellipse1 = new Ellipse(0, 0, 60, 40);
            ellipse1.setStroke(Color.BLACK);
            if (!isFilled.isSelected())
                ellipse1.setFill(null);
            stackPane.getChildren().add(ellipse1);
        });
        isFilled.setOnAction(e -> {
            if (stackPane.getChildren().size() != 0 && stackPane.getChildren().get(0) instanceof Shape)
                if (isFilled.isSelected())
                    ((Shape) stackPane.getChildren().get(0)).setFill(Color.BLACK);
                else
                    ((Shape) stackPane.getChildren().get(0)).setFill(null);
        });
    }
}
