package sample.exercise9;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

@FunctionalInterface
interface T1 {
    void m1();
}

public class RectangleParameters extends Pane {
    private static int counter = 1;
    private final Rectangle rectangle = new Rectangle();
    private final TextField xField;
    private final TextField yField;
    private final TextField wField;
    private final TextField hField;
    private T1 t1;


    public RectangleParameters(int x, int y, int width, int height) {
        setStyle("-fx-border-color: black");

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setHgap(5);
        pane.setVgap(10);

        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setHeight(height);
        rectangle.setWidth(width);

        pane.add(new Text("X: "), 0, 0);
        xField = new TextField(x + "");
        xField.setPrefColumnCount(5);
        xField.setAlignment(Pos.CENTER_LEFT);
        pane.add(xField, 1, 0);
        pane.add(new Text("Y: "), 0, 1);
        yField = new TextField(y + "");
        yField.setPrefColumnCount(5);
        yField.setAlignment(Pos.CENTER_LEFT);
        pane.add(yField, 1, 1);
        pane.add(new Text("Width: "), 0, 2);
        wField = new TextField(width + "");
        wField.setPrefColumnCount(5);
        wField.setAlignment(Pos.CENTER_LEFT);
        pane.add(wField, 1, 2);
        pane.add(new Text("Height: "), 0, 3);
        hField = new TextField(height + "");
        hField.setPrefColumnCount(5);
        hField.setAlignment(Pos.CENTER_LEFT);
        pane.add(hField, 1, 3);

        Label label = new Label("Enter circle " + counter++ + " info: ", pane);
        label.setContentDisplay(ContentDisplay.BOTTOM);

        getChildren().add(label);

        xField.setOnAction(e -> updateRectangle());
        yField.setOnAction(e -> updateRectangle());
        wField.setOnAction(e -> updateRectangle());
        hField.setOnAction(e -> updateRectangle());

        rectangle.setOnMouseDragged(e -> {
            rectangle.setX(e.getX());
            rectangle.setY(e.getY());
            updateParameters();
        });
    }


    public void updateRectangle() {
        rectangle.setX(Double.parseDouble(xField.getText()));
        rectangle.setY(Double.parseDouble(yField.getText()));
        rectangle.setWidth(Double.parseDouble(wField.getText()));
        rectangle.setHeight(Double.parseDouble(hField.getText()));
        t1.m1();
    }

    private void updateParameters() {
        xField.setText(rectangle.getX() + "");
        yField.setText(rectangle.getY() + "");
        t1.m1();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean intersects(RectangleParameters rectangleParameters) {
        Rectangle r = rectangleParameters.rectangle;
        return (((rectangle.getX() <= r.getX() && r.getX() <= rectangle.getX() + rectangle.getWidth()) ||
                (rectangle.getX() <= r.getX() + r.getWidth() && r.getX() + r.getWidth() <= rectangle.getX() + rectangle.getWidth()))
                && ((rectangle.getY() <= r.getY() && r.getY() <= rectangle.getY() + rectangle.getHeight()) ||
                (rectangle.getY() <= r.getY() + r.getHeight() && r.getY() + r.getHeight() <= rectangle.getY() + rectangle.getHeight())));
    }

    public void setOnAction(T1 t1) {
        this.t1 = t1;
    }
}
