package sample.exercise18;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ControlFan extends BorderPane {
    Fan fan = new Fan(50);

    public ControlFan() {

        Button pause = new Button("Pause");
        Button resume = new Button("Resume");
        Button reverse = new Button("Reverse");
        final HBox node = new HBox(pause, resume, reverse);
        node.setPadding(new Insets(10, 10, 10, 10));
        node.setSpacing(10);
        setTop(node);
        pause.setOnAction(e -> fan.pause());
        resume.setOnAction(e -> fan.play());
        reverse.setOnAction(e -> fan.reverse());

        setCenter(fan);

        Slider slSpeed = new Slider();
        slSpeed.setMax(40);
        slSpeed.setValue(fan.getSpeed());
        slSpeed.valueProperty().addListener(e -> fan.setSpeed(slSpeed.getValue()));
        setBottom(slSpeed);
    }
}
