package pt.scalablesolutions.client.application;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.gwtplatform.mvp.client.View;

public interface EditorView<T> extends View, Editor<T> {
    SimpleBeanEditorDriver<T, ?> createEditorDriver();
}
