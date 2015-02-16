package pt.scalablesolutions.client.editor;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.text.shared.Parser;

import java.text.ParseException;
import java.util.Date;

public class DateParser implements Parser<Date> {

    private final DateTimeFormat dateTimeFormat;

    public DateParser(DateTimeFormat dateTimeFormat) {

        this.dateTimeFormat = dateTimeFormat;
    }

    @Override
    public Date parse(CharSequence object) throws ParseException {
        if ("".equals(object.toString())) {
            return null;
        }
        try {
            return dateTimeFormat.parseStrict(object.toString());
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }
}

