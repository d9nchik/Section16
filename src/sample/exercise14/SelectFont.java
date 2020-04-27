package sample.exercise14;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SelectFont extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: aliceblue");

        Text text = new Text("Programming is fun");
        text.xProperty().bind(pane.widthProperty().divide(4));
        text.yProperty().bind(pane.heightProperty().divide(2));
        pane.setCenter(new Pane(text));

        HBox checkButtons = new HBox();
        checkButtons.setAlignment(Pos.CENTER);
        checkButtons.setSpacing(5);
        CheckBox bold = new CheckBox("Bold");
        CheckBox italic = new CheckBox("Italic");
        checkButtons.getChildren().addAll(bold, italic);
        pane.setBottom(checkButtons);

        HBox hBox = new HBox();
        hBox.setSpacing(5);
        ComboBox<String> fonts = new ComboBox<>();
        fonts.getItems().addAll(Font.getFontNames());
        fonts.getSelectionModel().select(text.getFont().getName());
        Label fontLabel = new Label("Font name ", fonts);
        fontLabel.setContentDisplay(ContentDisplay.RIGHT);
        ComboBox<Integer> fontSize = new ComboBox<>();
        for (int i = 1; i < 100; i++)
            fontSize.getItems().add(i);
        fontSize.getSelectionModel().select(Integer.valueOf((int) text.getFont().getSize()));
        Label fontSizeLabel = new Label("Font Size ", fontSize);
        fontSizeLabel.setContentDisplay(ContentDisplay.RIGHT);
        hBox.getChildren().addAll(fontLabel, fontSizeLabel);
        pane.setTop(hBox);

        Scene scene = new Scene(pane, 440, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 14");
        primaryStage.show();

        final EventHandler<ActionEvent> actionEventEventHandler = getActionEventEventHandler(text, bold, italic, fonts, fontSize);
        bold.setOnAction(actionEventEventHandler);
        italic.setOnAction(actionEventEventHandler);
        fonts.setOnAction(actionEventEventHandler);
        fontSize.setOnAction(actionEventEventHandler);

    }

    private EventHandler<ActionEvent> getActionEventEventHandler(Text text, CheckBox bold, CheckBox italic, ComboBox<String> fonts, ComboBox<Integer> fontSize) {
        return e -> text.setFont(Font.font(fonts.getValue(), bold.isSelected() ? FontWeight.BOLD : FontWeight.NORMAL,
                italic.isSelected() ? FontPosture.ITALIC : FontPosture.REGULAR, fontSize.getValue()));
    }
}
