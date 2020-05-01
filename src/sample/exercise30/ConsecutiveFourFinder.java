package sample.exercise30;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class ConsecutiveFourFinder extends Application {

    private TextField[] chosenFour;

    public static void main(String[] args) {
        launch(args);
    }

    private static void colourTextFields(TextField... fields) {
        for (TextField field : fields)
            field.setStyle("-fx-border-color: red");
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        GridPane scheme = new GridPane();
        pane.setCenter(scheme);
        TextField[][] cells = new TextField[6][7];

        for (int i = 0; i < 6; i++) {
            Random random = new Random();
            for (int j = 0; j < 7; j++) {
                TextField currentCell = new TextField("" + random.nextInt(10));
                currentCell.setPrefColumnCount(1);
                cells[i][j] = currentCell;
                scheme.add(currentCell, j, i);
            }
        }

        final Text text = new Text();
        pane.setTop(new StackPane(text));

        final Button solve = new Button("Solve");
        pane.setBottom(new StackPane(solve));

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 30");
        primaryStage.show();

        scene.setOnMouseClicked(e -> clear());
        solve.setOnAction(e -> {
            if (isConsecutiveFour(cells))
                text.setText("A consecutive four found");
            else
                text.setText("A consecutive four not found");
        });
    }

    private boolean isConsecutiveFour(TextField[][] values) {
        clear();
        chosenFour = new TextField[4];

        if (values.length < 4)
            return false;
        for (TextField[] value : values) {
            for (int k = 0; k < values.length - 3; k++) {
                System.arraycopy(value, k, chosenFour, 0, 4);
                if (isConsecutiveFour()) return true;
            }
        }

        for (int k = 0; k < values.length - 3; k++) {
            for (int i = 0; i < values[k].length; i++) {
                for (int j = 0; j < 4; j++)
                    chosenFour[j] = values[k + j][i];
                if (isConsecutiveFour()) return true;
            }
        }

        for (int k = 0; k < values.length - 3; k++) {
            for (int i = 0; i < values[k].length - 3; i++) {
                for (int j = 0; j < 4; j++)
                    chosenFour[j] = values[k + j][i + j];
                if (isConsecutiveFour()) return true;
            }
        }

        for (int k = 0; k < values.length - 3; k++) {
            for (int i = values[k].length - 4; i >= 0; i--) {
                for (int j = 0; j < 4; j++)
                    chosenFour[j] = values[k + j][i - j + 3];
                if (isConsecutiveFour()) return true;
            }
        }

        chosenFour = null;
        return false;
    }

    private boolean isConsecutiveFour() {
        if (areTextFieldsEqual(chosenFour)) {
            colourTextFields(chosenFour);
            return true;
        }
        return false;
    }

    private boolean areTextFieldsEqual(TextField... textFields) {
        for (TextField field : textFields)
            if (!textFields[0].getText().equals(field.getText()))
                return false;
        return true;
    }

    private void clear() {
        if (chosenFour != null) {
            for (TextField textfield : chosenFour)
                textfield.setStyle("-fx-border-color: lightgray");
            chosenFour = null;
        }
    }
}
