package sample.exercise16;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DemonstrationOfListView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll("China", "Japan", "Korea", "India", "Malaysia", "Vietnam", "Ukraine",
                "Russia", "Germany", "France", "England", "Spain", "Italy", "Hungary", "USA");
        pane.setCenter(listView);

        ComboBox<SelectionMode> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(SelectionMode.values());
        comboBox.getSelectionModel().select(listView.getSelectionModel().getSelectionMode());
        final Label node = new Label("Choose Selection Mode: ", comboBox);
        node.setContentDisplay(ContentDisplay.RIGHT);
        pane.setTop(node);

        Text text = new Text("Selected items are");
        pane.setBottom(text);

        Scene scene = new Scene(pane, 250, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 16");
        primaryStage.show();

        comboBox.setOnAction(e -> listView.getSelectionModel().setSelectionMode(comboBox.getValue()));
        listView.getSelectionModel().selectedItemProperty().addListener(e -> {
            StringBuilder countryLine = new StringBuilder("Selected items are");
            for (String country : listView.getSelectionModel().getSelectedItems())
                countryLine.append(" ").append(country);
            text.setText(countryLine.toString());
        });
    }
}
