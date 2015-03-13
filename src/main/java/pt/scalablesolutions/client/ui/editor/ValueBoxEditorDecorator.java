package pt.scalablesolutions.client.ui.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

import java.util.List;

/**
 * A simple decorator to display leaf widgets with an error message.
 * <p/>
 * <h3>Use in UiBinder Templates</h3>
 * <p/>
 * The decorator may have exactly one ValueBoxBase added though an
 * <code>&lt;e:valuebox></code> child tag.
 * <p/>
 * For example:
 * <pre>
 * &#64;UiField
 * ValueBoxEditorDecorator&lt;String&gt; name;
 * </pre>
 * <pre>
 * &lt;e:ValueBoxEditorDecorator ui:field='name'>
 *   &lt;e:valuebox>
 *     &lt;g:TextBox />
 *   &lt;/e:valuebox>
 * &lt;/e:ValueBoxEditorDecorator>
 * </pre>
 *
 * @param <T> the type of data being edited
 */
public class ValueBoxEditorDecorator<T> extends Composite implements
        HasEditorErrors<T>, IsEditor<ValueBoxEditor<T>> {
    @UiField
    SimplePanel contents;
    @UiField
    Image errorImage;
    private ValueBoxEditor<T> editor;

    /**
     * Constructs a ValueBoxEditorDecorator.
     */
    @UiConstructor
    public ValueBoxEditorDecorator() {
        initWidget(Binder.BINDER.createAndBindUi(this));
    }

    /**
     * Constructs a ValueBoxEditorDecorator using a {@link com.google.gwt.user.client.ui.ValueBoxBase}
     * widget and a {@link ValueBoxEditor} editor.
     *
     * @param widget the widget
     * @param editor the editor
     */
    public ValueBoxEditorDecorator(ValueBoxBase<T> widget,
                                   ValueBoxEditor<T> editor) {
        this();
        contents.add(widget);
        this.editor = editor;
    }

    /**
     * Returns the associated {@link ValueBoxEditor}.
     *
     * @return a {@link ValueBoxEditor} instance
     * @see #setEditor(ValueBoxEditor)
     */
    public ValueBoxEditor<T> asEditor() {
        return editor;
    }

    /**
     * Sets the associated {@link ValueBoxEditor}.
     *
     * @param editor a {@link ValueBoxEditor} instance
     * @see #asEditor()
     */
    public void setEditor(ValueBoxEditor<T> editor) {
        this.editor = editor;
    }

    /**
     * Set the widget that the EditorPanel will display. This method will
     * automatically call {@link #setEditor}.
     *
     * @param widget a {@link ValueBoxBase} widget
     */
    @UiChild(limit = 1, tagname = "valuebox")
    public void setValueBox(ValueBoxBase<T> widget) {
        contents.add(widget);
        setEditor(widget.asEditor());
    }

    /**
     * The default implementation will display, but not consume, received errors
     * whose {@link com.google.gwt.editor.client.EditorError#getEditor() getEditor()} method returns the Editor
     * passed into {@link #setEditor}.
     *
     * @param errors a List of {@link com.google.gwt.editor.client.EditorError} instances
     */
    public void showErrors(List<EditorError> errors) {
        StringBuilder sb = new StringBuilder();
        for (EditorError error : errors) {
            if (error.getEditor().equals(editor)) {
                sb.append("\n").append(error.getMessage());
            }
        }

        if (sb.length() == 0) {
            errorImage.setVisible(false);
            errorImage.setTitle("");
            return;
        }

        errorImage.setTitle(sb.substring(1));
        errorImage.setVisible(true);
    }

    interface Binder extends UiBinder<Widget, ValueBoxEditorDecorator<?>> {
        Binder BINDER = GWT.create(Binder.class);
    }
}
