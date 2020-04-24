package sample.exercise5;

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

        Text textDecimal = new Text("Decimal");
        Text textHex = new Text("Hex");
        Text textBinary = new Text("Binary");
        pane.add(textDecimal, 0, 0);
        pane.add(textHex, 0, 1);
        pane.add(textBinary, 0, 2);

        TextField decimal = new TextField();
        TextField hex = new TextField();
        TextField binary = new TextField();
        pane.add(decimal, 1, 0);
        pane.add(hex, 1, 1);
        pane.add(binary, 1, 2);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 4");
        primaryStage.show();

        decimal.setOnAction(e -> {
            int number = Integer.parseInt(decimal.getText());
            hex.setText(Integer.toHexString(number));
            binary.setText(Integer.toBinaryString(number));
        });

        hex.setOnAction(e -> {
            int number = Integer.parseInt(hex.getText(), 16);
            decimal.setText(number + "");
            binary.setText(Integer.toBinaryString(number));
        });

        binary.setOnAction(e -> {
            int number = Integer.parseInt(binary.getText(), 2);
            decimal.setText(number + "");
            hex.setText(Integer.toHexString(number));
        });
    }
}
