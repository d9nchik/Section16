package sample.exercise31;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleWithParameter extends StackPane {
    private final int RADIUS = 15;
    private final Circle circle = new Circle(RADIUS);
    private int x, y;

    public CircleWithParameter() {
        getChildren().add(circle);
        circle.setRadius(RADIUS);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
    }

    public CircleWithParameter(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public static void highlight(CircleWithParameter... circleWithParameters) {
        for (CircleWithParameter circleWithParameter : circleWithParameters)
            circleWithParameter.highlight();
    }

    public void setYellow() {
        if (isNotSet())
            circle.setFill(Color.YELLOW);
    }

    public void setRed() {
        if (isNotSet())
            circle.setFill(Color.RED);
    }

    private void highlight() {
        circle.setStroke(Color.ORANGE);
    }

    public boolean isNotSet() {
        return circle.getFill().equals(Color.WHITE);
    }

    public boolean isRed() {
        return circle.getFill().equals(Color.RED);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}