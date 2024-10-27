package wx82.agrotech.groweasyapi.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    /**
     * Converts a String to LocalDateTime using the specified pattern.
     *
     * @param dateTimeString the date-time string to convert
     * @param pattern the pattern to use for parsing
     * @return the LocalDateTime object
     * @throws DateTimeParseException if the text cannot be parsed
     */
    public static LocalDateTime convertStringToLocalDateTime(String dateTimeString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTimeString, formatter);
    }
}
