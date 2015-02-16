package pt.scalablesolutions.client.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

import java.util.Date;
import java.util.List;

/**
 * Created by nunograca on 14/02/15.
 */
public class DateBoxEditorDecorator extends Composite implements
        HasEditorErrors<Date>, IsEditor<LeafValueEditor<Date>> {

    interface Binder extends UiBinder<Widget, DateBoxEditorDecorator> {
        Binder BINDER = GWT.create(Binder.class);
    }

    @UiField
    SimplePanel contents;

    @UiField
    DivElement errorLabel;

    private LeafValueEditor<Date> editor;
    
    @UiConstructor
    public DateBoxEditorDecorator() {
        initWidget(Binder.BINDER.createAndBindUi(this));
    }

    /**
     * Set the widget that the EditorPanel will display. This method will
     * automatically call {@link #setEditor}.
     *
     * @param widget a {@link com.google.gwt.user.datepicker.client.DateBox} widget
     */
    @UiChild(limit = 1, tagname = "datebox")
    public void setValueBox(DateBox widget) {
        contents.add(widget);
        setEditor(widget.asEditor());
    }

    /**
     * Sets the associated {@link ValueBoxEditor}.
     *
     * @param editor a {@link ValueBoxEditor} instance
     * @see #asEditor()
     */
    public void setEditor(LeafValueEditor<Date> editor) {
        this.editor = editor;
    }

    /**
     * Returns the associated {@link ValueBoxEditor}.
     *
     * @return a {@link ValueBoxEditor} instance
     * @see #setEditor(LeafValueEditor)
     */
    @Override
    public LeafValueEditor<Date> asEditor() {
        return editor;
    }

    /**
     * The default implementation will display, but not consume, received errors
     * whose {@link EditorError#getEditor() getEditor()} method returns the Editor
     * passed into {@link #setEditor}.
     *
     * @param errors a List of {@link EditorError} instances
     */
    public void showErrors(List<EditorError> errors) {
        StringBuilder sb = new StringBuilder();
        for (EditorError error : errors) {
            if (error.getEditor().equals(editor)) {
                sb.append("\n").append(error.getMessage());
            }
        }

        if (sb.length() == 0) {
            errorLabel.setInnerText("");
            errorLabel.getStyle().setDisplay(Style.Display.NONE);
            return;
        }

        errorLabel.setInnerText(sb.substring(1));
        errorLabel.getStyle().setDisplay(Style.Display.INLINE_BLOCK);
    }
}
