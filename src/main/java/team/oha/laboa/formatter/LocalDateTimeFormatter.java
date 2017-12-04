package team.oha.laboa.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/4
 * @modified
 */
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        return LocalDateTime.parse(text, formatter);
    }

    @Override
    public String print(LocalDateTime object, Locale locale) {
        return object.format(formatter);
    }
}
