package sample.exercise18;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

class Fan extends Pane {
    private boolean reverse;
    private double speed = 10;
    private final Timeline animation = new Timeline(new KeyFrame(Duration.millis(200), e -> rotate()));

    public Fan(double radius) {

        for (int i = 0; i < 6; i++) {
            Arc arc = new Arc(0, 0, radius, radius, 30 * (2 * i + 1), 30);
            arc.centerXProperty().bind(widthProperty().divide(2).add(getLayoutX()));
            arc.centerYProperty().bind(heightProperty().divide(2).add(getLayoutY()));
            if (i % 2 == 0)
                arc.setFill(Color.ORANGE);
            arc.setType(ArcType.ROUND);
            getChildren().add(arc);
        }

        Circle circle = new Circle(0, 0, radius);
        circle.centerXProperty().bind(widthProperty().divide(2).add(getLayoutX()));
        circle.centerYProperty().bind(heightProperty().divide(2).add(getLayoutY()));
        circle.setFill(null);
        circle.setStroke(Color.BLACK);
        getChildren().add(circle);

        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    private void rotate() {
        if (reverse)
            setRotate(getRotate() - speed);
        else
            setRotate(getRotate() + speed);
    }

    public void play() {
        animation.play();
    }

    public void stop() {
        animation.stop();
    }

    public void pause() {
        animation.pause();
    }

    public void reverse() {
        reverse = !reverse;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
