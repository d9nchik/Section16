package sample.exercise13;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoanCompare extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        HBox hbox = new HBox();
        hbox.setSpacing(5);
        pane.setTop(hbox);
        TextField amount = new TextField();
        amount.setPrefColumnCount(6);
        Label loanLabel = new Label("Loan Amount: ", amount);
        loanLabel.setContentDisplay(ContentDisplay.RIGHT);
        TextField numberYears = new TextField();
        numberYears.setPrefColumnCount(2);
        Label yearLabel = new Label("Number of years: ", numberYears);
        yearLabel.setContentDisplay(ContentDisplay.RIGHT);
        Button showTable = new Button("Show Table");
        hbox.getChildren().addAll(loanLabel, yearLabel, showTable);

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        pane.setCenter(textArea);

        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise 13");
        primaryStage.show();

        showTable.setOnAction(e -> {
            double loanAmount = Double.parseDouble(amount.getText());
            double numberOfYears = Double.parseDouble(numberYears.getText());

            textArea.setText("Interest Rate    Monthly Payment    Total Payment");

            for (double monthlyInterestRate = 5; monthlyInterestRate <= 8; monthlyInterestRate += 0.125) {

                double monthlyPayment = loanAmount * monthlyInterestRate / 1200.0 / (1
                        - 1 / Math.pow(1 + monthlyInterestRate / 1200.0, numberOfYears * 12));
                double totalPayment = monthlyPayment * numberOfYears * 12;
                textArea.setText(textArea.getText() + "\n" +
                        String.format("%-6.3f%%               %6.2f           %8.2f",
                                monthlyInterestRate, monthlyPayment, totalPayment));
            }
        });
    }
}
