package sample.exercise8;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

@FunctionalInterface
interface T1 {
    void m1();
}

public class CircleParameters extends Pane {
    private static int counter = 1;
    private final Circle circle = new Circle();
    private final TextField xField;
    private final TextField yField;
    private final TextField rField;
    private T1 t1;


    public CircleParameters(int x, int y, int radius) {
        setStyle("-fx-border-color: black");

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setHgap(5);
        pane.setVgap(10);

        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(radius);

        pane.add(new Text("Center x: "), 0, 0);
        xField = new TextField(x + "");
        xField.setPrefColumnCount(5);
        xField.setAlignment(Pos.CENTER_RIGHT);
        pane.add(xField, 1, 0);
        pane.add(new Text("Center y: "), 0, 1);
        yField = new TextField(y + "");
        yField.setPrefColumnCount(5);
        yField.setAlignment(Pos.CENTER_RIGHT);
        pane.add(yField, 1, 1);
        pane.add(new Text("Radius: "), 0, 2);
        rField = new TextField(radius + "");
        rField.setPrefColumnCount(5);
        rField.setAlignment(Pos.CENTER_RIGHT);
        pane.add(rField, 1, 2);

        Label label = new Label("Enter circle " + counter++ + " info: ", pane);
        label.setContentDisplay(ContentDisplay.BOTTOM);

        getChildren().add(label);

        xField.setOnAction(e -> updateCircle());
        yField.setOnAction(e -> updateCircle());
        rField.setOnAction(e -> updateCircle());

        circle.setOnMouseDragged(e -> {
            circle.setCenterX(e.getX());
            circle.setCenterY(e.getY());
            updateParameters();
        });
    }

    private static double distance(Circle circle1, Circle circle2) {
        return Math.sqrt(Math.pow(circle1.getCenterX() - circle2.getCenterX(), 2)
                + Math.pow(circle1.getCenterY() - circle2.getCenterX(), 2));
    }

    public void updateCircle() {
        circle.setCenterX(Double.parseDouble(xField.getText()));
        circle.setCenterY(Double.parseDouble(yField.getText()));
        circle.setRadius(Double.parseDouble(rField.getText()));
        t1.m1();
    }

    private void updateParameters() {
        xField.setText(circle.getCenterX() + "");
        yField.setText(circle.getCenterY() + "");
        t1.m1();
    }

    public Circle getCircle() {
        return circle;
    }

    public boolean intersects(CircleParameters circleParameters) {
        double radiusSum = circle.getRadius() + circleParameters.circle.getRadius();
        return radiusSum > distance(circle, circleParameters.circle);
    }

    public void setOnAction(T1 t1) {
        this.t1 = t1;
    }
}
