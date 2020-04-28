package sample.exercise19;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.exercise18.ControlFan;

public class MultipleFans extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        final HBox hBox = new HBox(new ControlFan(), new ControlFan(), new ControlFan());
        pane.setCenter(hBox);

        Button start = new Button("Start All");
        Button stop = new Button("Stop All");
        HBox buttons = new HBox(start, stop);
        buttons.setSpacing(5);
        buttons.setAlignment(Pos.CENTER);
        pane.setBottom(buttons);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 19");
        primaryStage.show();

        start.setOnAction(e -> {
            for (Node my : hBox.getChildren())
                if (my instanceof ControlFan)
                    ((ControlFan) my).play();
        });

        stop.setOnAction(e -> {
            for (Node my : hBox.getChildren())
                if (my instanceof ControlFan)
                    ((ControlFan) my).stop();
        });
    }
}
