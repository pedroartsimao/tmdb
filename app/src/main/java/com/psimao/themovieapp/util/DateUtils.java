package com.psimao.themovieapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String formatMinutesToHoursAndMinutes(int inputMinutes) {
        int hours = inputMinutes / 60;
        int minutes = inputMinutes % 60;
        return String.format(Locale.getDefault(), "%dh %02dm", hours, minutes);
    }

    public static String formatDateToString(Date date) {
        return new SimpleDateFormat("dd MMMM yyyy", Locale.US).format(date);
    }
}
