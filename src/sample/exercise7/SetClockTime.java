package sample.exercise7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SetClockTime extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        ClockPane clockPane = new ClockPane();
        pane.setCenter(clockPane);

        HBox timeTextField = new HBox();
        pane.setBottom(timeTextField);
        TextField hour = new TextField(clockPane.getHour() + "");
        hour.setPrefColumnCount(2);
        Label labelHour = new Label("Hour", hour);
        labelHour.setContentDisplay(ContentDisplay.RIGHT);
        TextField minute = new TextField(clockPane.getMinute() + "");
        minute.setPrefColumnCount(2);
        Label labelMinute = new Label("Minute", minute);
        labelMinute.setContentDisplay(ContentDisplay.RIGHT);
        TextField second = new TextField(clockPane.getSecond() + "");
        second.setPrefColumnCount(2);
        Label labelSecond = new Label("Second", second);
        labelSecond.setContentDisplay(ContentDisplay.RIGHT);
        timeTextField.getChildren().addAll(labelHour, labelMinute, labelSecond);

        Scene scene = new Scene(pane, 225, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 7");
        primaryStage.show();

        hour.setOnAction(e -> clockPane.setHour(Integer.parseInt(hour.getText())));
        minute.setOnAction(e -> clockPane.setMinute(Integer.parseInt(minute.getText())));
        second.setOnAction(e -> clockPane.setSecond(Integer.parseInt(second.getText())));
    }
}
