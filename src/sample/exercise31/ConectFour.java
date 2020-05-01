package sample.exercise31;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConectFour extends Application {

    private final CircleWithParameter[][] cells = new CircleWithParameter[6][7];
    private final BorderPane pane = new BorderPane();
    private boolean redTurn;
    private boolean win;
    private int stepsLeft = 42;

    public static void main(String[] args) {
        launch(args);
    }

    private static boolean areCirclesEqual(CircleWithParameter... circleWithParameters) {
        if (circleWithParameters[0].isNotSet())
            return false;
        for (CircleWithParameter circleWithParameter : circleWithParameters)
            if ((circleWithParameters[0].isRed() != circleWithParameter.isRed()) ||
                    circleWithParameter.isNotSet())
                return false;
        return true;
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane scheme = new GridPane();
        pane.setCenter(scheme);
        scheme.setStyle("-fx-background-color: aquamarine");
        scheme.setHgap(5);
        scheme.setVgap(5);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                cells[i][j] = new CircleWithParameter(i, j);
                cells[i][j].setOnMouseClicked(e -> {
                    CircleWithParameter circleWithParameter = ((CircleWithParameter) e.getSource());
                    makeTurn(circleWithParameter.getX(), circleWithParameter.getY());
                });
                scheme.add(cells[i][j], j, i);
            }
        }

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 31");
        primaryStage.show();
    }

    private void makeTurn(int i, int j) {
        if (cells[i][j].isNotSet() && !win) {
            for (int k = cells.length - 1; k > i; k--) {
                if (cells[k][j].isNotSet())
                    return;
            }
            if (redTurn)
                cells[i][j].setRed();
            else
                cells[i][j].setYellow();
            redTurn = !redTurn;
            checkWin();
            stepsLeft--;
            if (!win && stepsLeft == 0) {
                pane.setTop(new StackPane(new Text("It`s draw!")));
            }
        }
    }

    private void checkWin() {
        CircleWithParameter[] chosenFour = new CircleWithParameter[4];

        if (cells.length < 4)
            return;

        for (CircleWithParameter[] value : cells) {
            for (int k = 0; k < cells[0].length - 3; k++) {
                System.arraycopy(value, k, chosenFour, 0, 4);
                if (isConsecutiveFour(chosenFour)) return;
            }
        }

        for (int k = 0; k < cells.length - 3; k++) {
            for (int i = 0; i < cells[k].length; i++) {
                for (int j = 0; j < 4; j++)
                    chosenFour[j] = cells[k + j][i];
                if (isConsecutiveFour(chosenFour)) return;
            }
        }

        for (int k = 0; k < cells.length - 3; k++) {
            for (int i = 0; i < cells[k].length - 3; i++) {
                for (int j = 0; j < 4; j++)
                    chosenFour[j] = cells[k + j][i + j];
                if (isConsecutiveFour(chosenFour)) return;
            }
        }

        for (int k = 0; k < cells.length - 3; k++) {
            for (int i = cells[k].length - 4; i >= 0; i--) {
                for (int j = 0; j < 4; j++)
                    chosenFour[j] = cells[k + j][i - j + 3];
                if (isConsecutiveFour(chosenFour)) return;
            }
        }
    }

    private boolean isConsecutiveFour(CircleWithParameter[] chosenFour) {
        if (areCirclesEqual(chosenFour)) {
            CircleWithParameter.highlight(chosenFour);
            win = true;
            return true;
        }
        return false;
    }


}
