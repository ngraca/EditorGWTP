package pt.scalablesolutions.client.validation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;
import pt.scalablesolutions.client.application.home.Person;

import javax.validation.Validator;
import javax.validation.groups.Default;

/**
 * Created by nunograca on 09/02/15.
 */
public class ValidatorFactory extends AbstractGwtValidatorFactory {
    
    @GwtValidation(value = Person.class, groups = Default.class)
    public interface GWTValidator extends Validator {
    }
    
    @Override
    public AbstractGwtValidator createValidator() {
        return GWT.create(GWTValidator.class);
    }
}
