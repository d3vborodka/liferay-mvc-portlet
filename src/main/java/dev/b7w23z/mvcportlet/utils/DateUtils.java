package dev.b7w23z.mvcportlet.utils;



import com.liferay.petra.string.StringPool;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

public final class DateUtils {

    public static final DateTimeFormatter DF_RU =
            DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.FULL)
                    .withLocale(new Locale("ru"));

    public static String format(Date date) {
        if ( date == null ) {
            return StringPool.BLANK;
        }
        return DF_RU.format(toLocalDate(date));
    }

    public static LocalDate toLocalDate(Date date) {
        if ( date == null ) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
