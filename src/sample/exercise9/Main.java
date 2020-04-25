package sample.exercise9;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);

        RectangleParameters rectangleParameters1 = new RectangleParameters(79, 20, 40, 50);
        RectangleParameters rectangleParameters2 = new RectangleParameters(110, 33, 50, 20);

        Text intersectMessage = new Text("Two rectangles intersects? Yes");
        intersectMessage.setY(15);
        intersectMessage.xProperty().bind(vBox.widthProperty().divide(4));
        vBox.getChildren().add(new Pane(rectangleParameters1.getRectangle(),
                rectangleParameters2.getRectangle(), intersectMessage));

        final HBox hBox = new HBox(rectangleParameters1, rectangleParameters2);
        hBox.setSpacing(10);
        vBox.getChildren().add(hBox);

        Button redraw = new Button("Redraw Circles");
        redraw.setOnAction(e -> {
            rectangleParameters1.updateRectangle();
            rectangleParameters2.updateRectangle();
        });
        vBox.getChildren().add(redraw);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 9");
        primaryStage.show();

        rectangleParameters1.setOnAction(() -> setIntersectMessage(rectangleParameters1, rectangleParameters2, intersectMessage));
        rectangleParameters2.setOnAction(() -> setIntersectMessage(rectangleParameters1, rectangleParameters2, intersectMessage));
    }

    private void setIntersectMessage(RectangleParameters rectangleParameters1, RectangleParameters rectangleParameters2,
                                     Text intersectMessage) {
        if (rectangleParameters1.intersects(rectangleParameters2))
            intersectMessage.setText("Two rectangles intersects? Yes");
        else
            intersectMessage.setText("Two rectangles intersects? No");
    }
}
