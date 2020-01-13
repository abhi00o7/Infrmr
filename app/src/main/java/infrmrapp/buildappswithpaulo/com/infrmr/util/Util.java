package infrmrapp.buildappswithpaulo.com.infrmr.util;

import android.icu.text.SimpleDateFormat;
import android.os.Build;

import java.text.ParseException;
import java.util.Date;

/**
 */

public class Util {

    public static String dateFormatted(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = format.parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");

        return dateFormat.format(date);

    }
}
