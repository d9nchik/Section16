package sample.exercise15;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DemonstrateLabel extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();


        ImageView grapes = new ImageView("src/img/grapes.gif");
        Label label = new Label("Grapes", grapes);
        label.setAlignment(Pos.CENTER);
        pane.setCenter(label);

        HBox buttons = new HBox();
        pane.setTop(buttons);
        buttons.setSpacing(5);
        ComboBox<ContentDisplay> contentDisplay = new ComboBox<>();
        contentDisplay.getItems().addAll(ContentDisplay.values());
        contentDisplay.getSelectionModel().select(label.getContentDisplay());
        Label labelContent = new Label("contentDisplay: ", contentDisplay);
        labelContent.setContentDisplay(ContentDisplay.RIGHT);
        TextField graphicTextGap = new TextField(label.getGraphicTextGap() + "");
        graphicTextGap.setPrefColumnCount(3);
        Label labelTextGap = new Label("graphicTextGap: ", graphicTextGap);
        labelTextGap.setContentDisplay(ContentDisplay.RIGHT);
        buttons.getChildren().addAll(labelContent, labelTextGap);

        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 15");
        primaryStage.show();

        contentDisplay.setOnAction(e -> label.setContentDisplay(contentDisplay.getValue()));
        graphicTextGap.setOnAction(e -> label.setGraphicTextGap(Double.parseDouble(graphicTextGap.getText())));
    }
}
