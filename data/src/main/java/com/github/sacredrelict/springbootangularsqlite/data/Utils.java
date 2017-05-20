package com.github.sacredrelict.springbootangularsqlite.data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Oleg on 14.05.2017.
 */
public class Utils {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static String getFormattedDate(Date date) {
        return dateFormat.format(date);
    }

}
