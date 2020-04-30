package sample.exercise24;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MediaDemo extends Application {
    private static final String MEDIA_URL =
            "http://liveexample.pearsoncmg.com/common/sample.mp4";
    private final Text timeExpired = new Text();
    private Slider slTime;
    private String tail;
    private MediaPlayer mediaPlayer;
    private final Timeline timeUpdate = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTime()));

    public static void main(String[] args) {
        launch(args);
    }

    private static String createTime(int hour, int minute, int second) {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    @Override
    public void start(Stage primaryStage) {
        timeUpdate.setCycleCount(Timeline.INDEFINITE);

        Media media = new Media(MEDIA_URL);
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        Button playButton = new Button(">");
        playButton.setOnAction(e -> {
            if (playButton.getText().equals(">")) {
                mediaPlayer.play();
                timeUpdate.play();
                playButton.setText("||");
            } else {
                mediaPlayer.pause();
                timeUpdate.pause();
                playButton.setText(">");
            }
        });

        Button rewindButton = new Button("<<");
        rewindButton.setOnAction(e -> mediaPlayer.seek(Duration.ZERO));

        slTime = new Slider();
        slTime.setPrefWidth(150);
        slTime.setMaxWidth(Region.USE_PREF_SIZE);
        slTime.setMinWidth(30);
        mediaPlayer.totalDurationProperty().addListener(e -> {
            final int seconds = (int) mediaPlayer.getTotalDuration().toSeconds();
            slTime.setMax(seconds);
            int second = seconds % 60;
            final int i = (seconds - second) / 60;
            int minute = i % 60;
            int hour = i / 60;
            tail = "/" + createTime(hour, minute, second);
        });

        Slider slVolume = new Slider();
        slVolume.setPrefWidth(150);
        slVolume.setMaxWidth(Region.USE_PREF_SIZE);
        slVolume.setMinWidth(30);
        slVolume.setValue(50);
        mediaPlayer.volumeProperty().bind(
                slVolume.valueProperty().divide(100));

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(playButton, new Label("Time"), slTime, timeExpired,
                new Label("Volume"), slVolume);

        BorderPane pane = new BorderPane();
        pane.setCenter(mediaView);
        pane.setBottom(hBox);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 650, 500);
        primaryStage.setTitle("MediaDemo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        final EventHandler<MouseEvent> mouseEventEventHandler = e -> mediaPlayer.seek(Duration.seconds(slTime.getValue()));
        slTime.setOnMouseDragged(mouseEventEventHandler);
        slTime.setOnMouseClicked(mouseEventEventHandler);

    }

    private void updateTime() {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.STOPPED)
            timeUpdate.stop();
        slTime.setValue(slTime.getValue() + 1);
        final int seconds = (int) mediaPlayer.getCurrentTime().toSeconds();
        int second = seconds % 60;
        final int i = (seconds - second) / 60;
        int minute = i % 60;
        int hour = i / 60;
        timeExpired.setText(createTime(hour, minute, second) + tail);
    }
}
