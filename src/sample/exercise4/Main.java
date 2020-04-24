package sample.exercise4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.BOTTOM_LEFT);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);

        Text textCelsius = new Text("Celsius");
        Text textFahrenheit = new Text("Fahrenheit");
        pane.add(textCelsius, 0, 0);
        pane.add(textFahrenheit, 0, 1);

        TextField celsius = new TextField();
        TextField fahrenheit = new TextField();
        pane.add(celsius, 1, 0);
        pane.add(fahrenheit, 1, 1);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 4");
        primaryStage.show();

        fahrenheit.setOnAction(e -> celsius.setText("" + ((Double.parseDouble(fahrenheit.getText()) - 32) * 5 / 9)));
        celsius.setOnAction(e -> fahrenheit.setText("" + (Double.parseDouble(celsius.getText()) * 9 / 5 + 32)));
    }
}
