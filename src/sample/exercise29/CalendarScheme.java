package sample.exercise29;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarScheme extends BorderPane {
    private final GregorianCalendar currentTime = new GregorianCalendar();
    private final String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December"};

    public CalendarScheme() {
        currentTime.set(Calendar.DATE, 1);
        draw();
    }

    private static void goMonthBack(GregorianCalendar currentTime) {
        int month = currentTime.get(Calendar.MONTH);
        if (month == GregorianCalendar.JANUARY) {
            final int year = currentTime.get(Calendar.YEAR);
            currentTime.set(Calendar.YEAR, year - 1);
            currentTime.set(Calendar.MONTH, Calendar.DECEMBER);
        } else {
            currentTime.set(Calendar.MONTH, month - 1);
        }
    }

    private void draw() {
        getChildren().clear();
        int month = currentTime.get(Calendar.MONTH);
        int year = currentTime.get(GregorianCalendar.YEAR);
        setTop(new StackPane(new Text(monthNames[month]
                + ", " + year)));

        GridPane scheme = new GridPane();
        setCenter(scheme);
        scheme.setAlignment(Pos.CENTER_LEFT);
        scheme.setVgap(5);
        scheme.setHgap(5);
        String[] dayOfWeeks = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (int i = 0; i < dayOfWeeks.length; i++)
            scheme.add(new Text(dayOfWeeks[i]), i, 0);

        getMainBodyOfCalendar(scheme);
    }

    private void getMainBodyOfCalendar(GridPane scheme) {
        int numberOfDaysInMonth = currentTime.getActualMaximum(Calendar.DAY_OF_MONTH);
        int startDay = currentTime.get(Calendar.DAY_OF_WEEK) - 1;

        int numberOfDaysInPreviousMonth = getNumberOfDaysInPreviousMonth();

        int startNumberInCalender = numberOfDaysInPreviousMonth - startDay + 1;

        getPreviousMonthDate(scheme, startDay, startNumberInCalender);

        int gridPaneCounter = 1;

        gridPaneCounter = getThisMonthDate(scheme, numberOfDaysInMonth, startDay, gridPaneCounter);

        addNextMonthDate(scheme, numberOfDaysInMonth, startDay, gridPaneCounter);
    }

    private void getPreviousMonthDate(GridPane scheme, int startDay, int startNumberInCalender) {
        for (int i = 0; i < startDay; i++) {
            final Text previousMonthDates = new Text(startNumberInCalender++ + "");
            previousMonthDates.setFill(Color.LIGHTGREY);
            scheme.add(previousMonthDates, i, 1);
        }
    }

    private int getThisMonthDate(GridPane scheme, int numberOfDaysInMonth, int startDay, int gridPaneCounter) {
        for (int i = 1; i <= numberOfDaysInMonth; i++) {
            scheme.add(new Text("" + i), (startDay - 1 + i) % 7, gridPaneCounter);

            if ((i + startDay) % 7 == 0)
                gridPaneCounter++;
        }
        return gridPaneCounter;
    }

    private void addNextMonthDate(GridPane scheme, int numberOfDaysInMonth, int startDay, int gridPaneCounter) {
        for (int i = 1; (startDay + numberOfDaysInMonth + i - 1) % 7 != 0; i++) {
            final Text afterDates = new Text("" + i);
            afterDates.setFill(Color.LIGHTGREY);
            scheme.add(afterDates, (startDay + numberOfDaysInMonth + i - 1) % 7, gridPaneCounter);
        }
    }

    private int getNumberOfDaysInPreviousMonth() {
        GregorianCalendar previousMonth = (GregorianCalendar) currentTime.clone();
        goMonthBack(previousMonth);
        return previousMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public void setPreviousMonth() {
        goMonthBack(currentTime);
        draw();
    }

    public void setNextMonth() {
        int month = currentTime.get(Calendar.MONTH);
        if (month == GregorianCalendar.DECEMBER) {
            final int year = currentTime.get(Calendar.YEAR);
            currentTime.set(Calendar.YEAR, year + 1);
            currentTime.set(Calendar.MONTH, Calendar.JANUARY);
        } else {
            currentTime.set(Calendar.MONTH, month + 1);
        }
        draw();
    }

}
