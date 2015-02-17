package pt.scalablesolutions.client.application.home;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.validation.client.impl.Validation;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import pt.scalablesolutions.client.application.ApplicationPresenter;
import pt.scalablesolutions.client.application.EditorView;
import pt.scalablesolutions.client.place.NameTokens;
import pt.scalablesolutions.client.validation.ViolationUtil;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class HomePagePresenter extends Presenter<HomePagePresenter.MyView, HomePagePresenter.MyProxy> {
    private SimpleBeanEditorDriver driver;

    @Inject
    HomePagePresenter(EventBus eventBus,
                      MyView view,
                      MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
    }

    @Override
    protected void onBind() {
        super.onBind();
        registerHandler(getView().getSaveButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                savePerson();
            }
        }));
    }

    private void savePerson() {
        Object person = driver.flush();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(person);
        if (driver.hasErrors()) {
            violations.addAll(ViolationUtil.convertErrorsIntoViolations(person, driver.getErrors()));
        }
        driver.setConstraintViolations(violations);
    }

    @Override
    protected void onReveal() {
        super.onReveal();
        driver = getView().createEditorDriver();
    }

    @Override
    protected void onReset() {
        super.onReset();
        driver.edit(new Person());
    }

    public interface MyView extends EditorView<Person> {
        HasClickHandlers getSaveButton();
    }

    @ProxyStandard
    @NameToken(NameTokens.home)
    public interface MyProxy extends ProxyPlace<HomePagePresenter> {
    }
}
