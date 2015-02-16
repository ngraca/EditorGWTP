package pt.scalablesolutions.client.application.home;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.gwtplatform.mvp.client.View;

/**
 * Created by nunograca on 08/02/15.
 */
public interface EditorView<T> extends View, Editor<T> {
    SimpleBeanEditorDriver<T, ?> createEditorDriver();
}
