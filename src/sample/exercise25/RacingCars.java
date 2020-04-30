package sample.exercise25;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RacingCars extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static void setSpeed(Car car, TextField carSpeed) {
        car.setSpeed(Integer.parseInt(carSpeed.getText()));
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();

        VBox cars = new VBox(car1, car2, car3, car4);
        pane.setCenter(cars);

        HBox controllerPanel = new HBox();
        controllerPanel.setAlignment(Pos.CENTER);
        controllerPanel.setSpacing(5);
        controllerPanel.setPadding(new Insets(5, 5, 5, 5));
        pane.setTop(controllerPanel);
        TextField carSpeed1 = new TextField("" + car1.getSpeed());
        carSpeed1.setPrefColumnCount(3);
        TextField carSpeed2 = new TextField("" + car2.getSpeed());
        carSpeed2.setPrefColumnCount(3);
        TextField carSpeed3 = new TextField("" + car3.getSpeed());
        carSpeed3.setPrefColumnCount(3);
        TextField carSpeed4 = new TextField("" + car4.getSpeed());
        carSpeed4.setPrefColumnCount(3);
        controllerPanel.getChildren().addAll(new Label("Car 1: "), carSpeed1, new Label("Car 2: "), carSpeed2,
                new Label("Car 3: "), carSpeed3, new Label("Car 4"), carSpeed4);

        Scene scene = new Scene(pane, 375, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 25");
        primaryStage.show();

        carSpeed1.setOnAction(e -> setSpeed(car1, carSpeed1));
        carSpeed2.setOnAction(e -> setSpeed(car2, carSpeed2));
        carSpeed3.setOnAction(e -> setSpeed(car3, carSpeed3));
        carSpeed4.setOnAction(e -> setSpeed(car4, carSpeed4));
    }
}
