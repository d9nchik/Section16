package sample.exercise29;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Calendar extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        CalendarScheme calendarScheme = new CalendarScheme();
        pane.setCenter(calendarScheme);

        HBox buttons = new HBox();
        pane.setBottom(buttons);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(5);
        Button prior = new Button("Prior");
        Button next = new Button("Next");
        buttons.getChildren().addAll(prior, next);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 29");
        primaryStage.show();

        prior.setOnAction(e -> calendarScheme.setPreviousMonth());
        next.setOnAction(e -> calendarScheme.setNextMonth());
    }
}
