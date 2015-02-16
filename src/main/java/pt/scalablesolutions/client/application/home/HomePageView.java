package pt.scalablesolutions.client.application.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.ui.client.ValueBoxEditorDecorator;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.gwtplatform.mvp.client.ViewImpl;
import pt.scalablesolutions.client.editor.DateBoxEditorDecorator;

import javax.inject.Inject;
import java.text.ParseException;
import java.util.Date;

public class HomePageView extends ViewImpl implements HomePagePresenter.MyView {

    public interface Binder extends UiBinder<Widget, HomePageView> {
    }

    public interface Driver extends SimpleBeanEditorDriver<Person, HomePageView> {
    }
    
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
    
    private HandlerManager handlerManager = new HandlerManager(this);
    
    @Inject
    HomePageView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public HasClickHandlers getSaveButton() {
        return save;
    }

    @Override
    public HandlerRegistration addHandler(DomEvent.Type<KeyUpHandler> type, KeyUpHandler keyUpHandler) {
        return handlerManager.addHandler(type, keyUpHandler);
    }

    @Override
    public SimpleBeanEditorDriver<Person, ?> createEditorDriver() {
        Driver driver = GWT.create(Driver.class);
        driver.initialize(this);
        return driver;
    }
    
    @UiHandler("save")
    void handleClick(ClickEvent event)
    {
//        Window.alert("Hello");
    }
    
//    @UiHandler({"password", "passwordVerify"})
//    void handleKeyUp(KeyUpEvent event)
//    {
//        handlerManager.fireEvent(event);
//    }
}
