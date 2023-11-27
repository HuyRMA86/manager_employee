package fa.training.convertor;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.isEmpty()) {
            setValue(null);
            return;
        }

        try {
            LocalDate date = LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            setValue(date);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd");
        }
    }

    @Override
    public String getAsText() {
        LocalDate date = (LocalDate) getValue();
        if (date == null) {
            return "";
        }
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
