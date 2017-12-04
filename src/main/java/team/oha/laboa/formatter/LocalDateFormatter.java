package team.oha.laboa.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
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
public class LocalDateFormatter implements Formatter<LocalDate> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, formatter);
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return object.format(formatter);
    }
}
