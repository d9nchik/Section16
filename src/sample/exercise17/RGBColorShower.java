package sample.exercise17;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RGBColorShower extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox pane = new VBox();
        pane.setSpacing(40);

        Text text = new Text("Show Colors");
        text.xProperty().bind(pane.widthProperty().divide(2).subtract(40));
        text.setY(40);
        text.setFont(Font.font("Times New Roman", 15));
        pane.getChildren().add(new Pane(text));

        GridPane sliders = new GridPane();
        sliders.setAlignment(Pos.CENTER);
        sliders.setHgap(5);
        pane.getChildren().add(sliders);
        sliders.add(new Text("Red: "), 0, 0);
        Slider r = new Slider();
        r.setMax(255);
        r.setShowTickLabels(true);
        r.setShowTickMarks(true);
        r.setMinorTickCount(3);
        sliders.add(r, 1, 0);
        sliders.add(new Text("Green: "), 0, 1);
        Slider g = new Slider();
        g.setMax(255);
        g.setShowTickLabels(true);
        g.setShowTickMarks(true);
        g.setMinorTickCount(3);
        sliders.add(g, 1, 1);
        sliders.add(new Text("Blue: "), 0, 2);
        Slider b = new Slider();
        b.setMax(255);
        b.setShowTickLabels(true);
        b.setShowTickMarks(true);
        b.setMinorTickCount(3);
        sliders.add(b, 1, 2);
        sliders.add(new Text("Opacity: "), 0, 3);
        Slider o = new Slider();
        o.setMax(255);
        o.setValue(255);
        o.setShowTickLabels(true);
        o.setShowTickMarks(true);
        o.setMinorTickCount(3);
        sliders.add(o, 1, 3);

        Scene scene = new Scene(pane, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 16");
        primaryStage.show();

        final InvalidationListener listener = e -> text.setFill(new Color(r.getValue() / 255,
                g.getValue() / 255, b.getValue() / 255, o.getValue() / 255));
        r.valueProperty().addListener(listener);
        g.valueProperty().addListener(listener);
        b.valueProperty().addListener(listener);
        o.valueProperty().addListener(listener);

    }
}
