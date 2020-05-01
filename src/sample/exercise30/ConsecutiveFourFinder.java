package sample.exercise30;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ConsecutiveFourFinder extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        GridPane scheme = new GridPane();
        pane.setCenter(scheme);
        TextField[][] cells = new TextField[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                TextField currentCell = new TextField();
                currentCell.setPrefColumnCount(1);
                cells[i][j] = currentCell;
                scheme.add(currentCell, j, i);
            }
        }

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 30");
        primaryStage.show();
    }
}
