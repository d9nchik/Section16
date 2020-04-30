package sample.exercise27;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ComboBoxDemo extends Application {
    // Declare an array of Strings for flag titles
    private final String[] flagTitles = {"Canada", "China", "Denmark",
            "France", "Germany", "India", "Norway", "United Kingdom",
            "United States of America"};

    // Declare an ImageView array for the national flags of 9 countries
    private final ImageView[] flagImage = {new ImageView("src/img/ca.gif"),
            new ImageView("src/img/china.gif"),
            new ImageView("src/img/denmark.gif"),
            new ImageView("src/img/fr.gif"),
            new ImageView("src/img/germany.gif"),
            new ImageView("src/img/india.gif"),
            new ImageView("src/img/norway.gif"),
            new ImageView("src/img/uk.gif"), new ImageView("src/img/us.gif")};

    // Declare and create a description pane
    private final DescriptionPane descriptionPane = new DescriptionPane();

    // Create a combo box for selecting countries
    private final ComboBox<String> cbo = new ComboBox<>(); // flagTitles;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set the first country (Canada) for display
        setDisplay(0);

        // Add combo box and description pane to the border pane
        BorderPane pane = new BorderPane();

        BorderPane paneForComboBox = new BorderPane();
        paneForComboBox.setLeft(new Label("Select a country: "));
        paneForComboBox.setCenter(cbo);
        pane.setTop(paneForComboBox);
        cbo.setPrefWidth(400);
        cbo.setValue("Canada");

        ObservableList<String> items =
                FXCollections.observableArrayList(flagTitles);
        cbo.getItems().addAll(items);
        pane.setCenter(descriptionPane);

        // Display the selected country
        cbo.setOnAction(e -> setDisplay(items.indexOf(cbo.getValue())));

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 450, 170);
        primaryStage.setTitle("ComboBoxDemo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * Set display information on the description pane
     */
    public void setDisplay(int index) {
        descriptionPane.setTitle(flagTitles[index]);
        descriptionPane.setImageView(flagImage[index]);
        descriptionPane.setDescription(readDescription(index));
    }

    private String readDescription(int i) {
        try (Scanner input = new Scanner(new BufferedInputStream(
                new FileInputStream("src/sample/exercise27/text/description" + i + ".txt")))) {
            StringBuilder stringBuilder = new StringBuilder();
            while (input.hasNext())
                stringBuilder.append(input.nextLine()).append("\n");
            return stringBuilder.toString();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
        return "";
    }
}
