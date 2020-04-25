package sample.exercise7;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class ClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;
    private boolean hourHandVisible = true;
    private boolean minuteHandVisible = true;
    private boolean secondHandVisible = true;

    /**
     * Construct a default clock with the current time
     */
    public ClockPane() {
        setCurrentTime();
    }

    /**
     * Construct a clock with specified hour, minute, and second
     */
    public ClockPane(int hour, int minute, int second) {
        this();
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }

    /**
     * Return hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Set a new hour
     */
    public void setHour(int hour) {
        if (hour < 12 && hour >= 0) {
            this.hour = hour;
            paintClock();
        }
    }

    /**
     * Return minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Set a new minute
     */
    public void setMinute(int minute) {
        if (minute < 60 && minute >= 0) {
            this.minute = minute;
            paintClock();
        }
    }

    /**
     * Return second
     */
    public int getSecond() {
        return second;
    }

    /**
     * Set a new second
     */
    public void setSecond(int second) {
        if (second < 60 && second >= 0) {
            this.second = second;
            paintClock();
        }
    }

    /* Set the current time for the clock */
    public void setCurrentTime() {
        // Construct a calendar for the current date and time
        Calendar calendar = new GregorianCalendar();

        // Set current hour, minute and second
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);

        paintClock(); // Repaint the clock
    }

    /**
     * Paint the clock
     */
    private void paintClock() {
        // Initialize clock parameters
        double clockRadius =
                Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;
        getChildren().clear();

        // Draw circle
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().add(circle);


        // Draw second hand
        if (secondHandVisible) {
            double sLength = clockRadius * 0.8;
            double secondX = centerX + sLength *
                    Math.sin(second * (2 * Math.PI / 60));
            double secondY = centerY - sLength *
                    Math.cos(second * (2 * Math.PI / 60));
            Line sLine = new Line(centerX, centerY, secondX, secondY);
            sLine.setStroke(Color.RED);
            getChildren().add(sLine);
        }

        // Draw minute hand
        if (minuteHandVisible) {
            double mLength = clockRadius * 0.65;
            double xMinute = centerX + mLength *
                    Math.sin(minute * (2 * Math.PI / 60));
            double minuteY = centerY - mLength *
                    Math.cos(minute * (2 * Math.PI / 60));
            Line mLine = new Line(centerX, centerY, xMinute, minuteY);
            mLine.setStroke(Color.BLUE);
            getChildren().add(mLine);
        }

        // Draw hour hand
        if (hourHandVisible) {
            double hLength = clockRadius * 0.5;
            double hourX = centerX + hLength *
                    Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
            double hourY = centerY - hLength *
                    Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
            Line hLine = new Line(centerX, centerY, hourX, hourY);
            hLine.setStroke(Color.GREEN);
            getChildren().add(hLine);
        }


        for (int i = 1; i <= 12; i++)
            getChildren().add(new Text(centerX - 5 + (clockRadius - 15) * Math.cos(Math.PI * (1 / 2.0 - i / 6.0)),
                    centerY + 5 - (clockRadius - 20) * Math.sin(Math.PI * (1 / 2.0 - i / 6.0)), (i + "")));
        for (int i = 1; i <= 60; i++) {
            double angle = Math.PI * (1 / 2.0 - i / 30.0);
            getChildren().add(new Line(centerX + clockRadius * Math.cos(angle), centerY + clockRadius * Math.sin(angle),
                    centerX + (clockRadius - 5) * Math.cos(angle), centerY + (clockRadius - 5) * Math.sin(angle)));
        }
        for (int i = 1; i <= 12; i++) {
            double angle = Math.PI * (1 / 2.0 - i / 6.0);
            getChildren().add(new Line(centerX + clockRadius * Math.cos(angle), centerY + clockRadius * Math.sin(angle),
                    centerX + (clockRadius - 10) * Math.cos(angle), centerY + (clockRadius - 10) * Math.sin(angle)));
        }
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintClock();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintClock();
    }

    public boolean isHourHandVisible() {
        return hourHandVisible;
    }

    public void setHourHandVisible(boolean hourHandVisible) {
        this.hourHandVisible = hourHandVisible;
    }

    public boolean isMinuteHandVisible() {
        return minuteHandVisible;
    }

    public void setMinuteHandVisible(boolean minuteHandVisible) {
        this.minuteHandVisible = minuteHandVisible;
    }

    public boolean isSecondHandVisible() {
        return secondHandVisible;
    }

    public void setSecondHandVisible(boolean secondHandVisible) {
        this.secondHandVisible = secondHandVisible;
    }

    public void setWithRandomValuesMinutesAndHour() {
        Random random = new Random();
        hour = random.nextInt(12);
        minute = random.nextInt(2) * 30;
    }
}
