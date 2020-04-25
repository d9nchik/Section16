package sample.exercise8;

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

        CircleParameters circleParameters1 = new CircleParameters(52, 60, 30);
        CircleParameters circleParameters2 = new CircleParameters(180, 56, 40);

        Text intersectMessage = new Text("Two circles intersects? No");
        intersectMessage.setY(15);
        intersectMessage.xProperty().bind(vBox.widthProperty().divide(4));
        vBox.getChildren().add(new Pane(circleParameters1.getCircle(),
                circleParameters2.getCircle(), intersectMessage));

        final HBox hBox = new HBox(circleParameters1, circleParameters2);
        hBox.setSpacing(10);
        vBox.getChildren().add(hBox);

        Button redraw = new Button("Redraw Circles");
        redraw.setOnAction(e -> {
            circleParameters1.updateCircle();
            circleParameters2.updateCircle();
        });
        vBox.getChildren().add(redraw);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 8");
        primaryStage.show();

        circleParameters1.setOnAction(() -> setIntersectMessage(circleParameters1, circleParameters2, intersectMessage));
        circleParameters2.setOnAction(() -> setIntersectMessage(circleParameters1, circleParameters2, intersectMessage));
    }

    private void setIntersectMessage(CircleParameters circleParameters1, CircleParameters circleParameters2, Text intersectMessage) {
        if (circleParameters1.intersects(circleParameters2))
            intersectMessage.setText("Two circles intersects? Yes");
        else
            intersectMessage.setText("Two circles intersects? No");
    }
}
