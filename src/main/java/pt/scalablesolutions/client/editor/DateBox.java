package pt.scalablesolutions.client.editor;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ValueBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import java.util.Date;

public class DateBox extends ValueBox<Date> {

    private class DateBoxHandler implements ValueChangeHandler<Date>,
            FocusHandler, ClickHandler, KeyDownHandler {

        public void onClick(ClickEvent event) {
            showDatePicker();
        }

        public void onFocus(FocusEvent event) {
            if (allowDPShow && isDatePickerShowing() == false) {
                showDatePicker();
            }
        }

        public void onKeyDown(KeyDownEvent event) {
            switch (event.getNativeKeyCode()) {
                case KeyCodes.KEY_ENTER:
                case KeyCodes.KEY_TAB:
                    updateDateFromTextBox();
                    // Deliberate fall through
                case KeyCodes.KEY_ESCAPE:
                case KeyCodes.KEY_UP:
                    hideDatePicker();
                    break;
                case KeyCodes.KEY_DOWN:
                    showDatePicker();
                    break;
            }
        }

        public void onValueChange(ValueChangeEvent<Date> event) {
            Date oldDate = getValue();
            setValue(event.getValue());
            DateChangeEvent.fireIfNotEqualDates(DateBox.this, oldDate, event.getValue());
            hideDatePicker();
            preventDatePickerPopup();
            DateBox.this.setFocus(true);
        }
    }

    private final PopupPanel popup;
    private final DatePicker picker;
    private boolean allowDPShow = true;

    public DateBox() {
        this(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT));
    }

    public DateBox(DateTimeFormat dateTimeFormat) {
        super(Document.get().createTextInputElement(), new DateTimeFormatRenderer(dateTimeFormat), new DateParser(dateTimeFormat));
        this.picker = new DatePicker();
        this.popup = new PopupPanel(true);

        popup.addAutoHidePartner(this.getElement());
        popup.setWidget(picker);
        popup.setStyleName("dateBoxPopup");

        DateBoxHandler handler = new DateBoxHandler();
        picker.addValueChangeHandler(handler);
        this.addFocusHandler(handler);
        this.addClickHandler(handler);
        this.addKeyDownHandler(handler);
        this.setDirectionEstimator(false);
    }

    /**
     * Hide the date picker.
     */
    public void hideDatePicker() {
        popup.hide();
    }

    /**
     * Returns true if date picker is currently showing, false if not.
     */
    public boolean isDatePickerShowing() {
        return popup.isShowing();
    }

    private void preventDatePickerPopup() {
        allowDPShow = false;
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            public void execute() {
                allowDPShow = true;
            }
        });
    }

    /**
     * Parses the current date box's value and shows that date.
     */
    public void showDatePicker() {
        Date current = getValue();
        if (current == null) {
            current = new Date();
        }
        picker.setCurrentMonth(current);
        popup.showRelativeTo(this);
    }

    private void updateDateFromTextBox() {
        setValue(picker.getValue(), true);
    }
}
