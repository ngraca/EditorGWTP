package pt.scalablesolutions.client.application.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import pt.scalablesolutions.client.ui.editor.ValueBoxEditorDecorator;

import javax.inject.Inject;
import java.util.Date;

public class HomePageView extends ViewImpl implements HomePagePresenter.MyView {

    @UiField
    ValueBoxEditorDecorator<String> name;
    @UiField
    ValueBoxEditorDecorator<String> password;
    @UiField
    ValueBoxEditorDecorator<String> passwordVerify;
    @UiField
    ValueBoxEditorDecorator<Integer> age;
    @UiField
    ValueBoxEditorDecorator<Date> birthDate;
    @UiField
    Button save;

    @Inject
    HomePageView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public HasClickHandlers getSaveButton() {
        return save;
    }

    @Override
    public SimpleBeanEditorDriver<Person, ?> createEditorDriver() {
        Driver driver = GWT.create(Driver.class);
        driver.initialize(this);
        return driver;
    }

    public interface Binder extends UiBinder<Widget, HomePageView> {
    }

    public interface Driver extends SimpleBeanEditorDriver<Person, HomePageView> {
    }
}
