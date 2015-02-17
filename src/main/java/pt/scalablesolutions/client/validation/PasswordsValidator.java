package pt.scalablesolutions.client.validation;

import pt.scalablesolutions.client.application.home.Person;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

public class PasswordsValidator implements ConstraintValidator<PasswordsValidator.PasswordsEqual, Person> {

    private String errorMessage;
    private String[] fields;

    @Override
    public void initialize(PasswordsEqual constraintAnnotation) {
        this.errorMessage = constraintAnnotation.message();
        this.fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Person value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        String password = value.getPassword();
        String passwordVerify = value.getPasswordVerify();
        boolean valid = (password == null && passwordVerify == null) || (password != null && password.equals(passwordVerify));
        if (!valid) {
            context.disableDefaultConstraintViolation();
            ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder = context.buildConstraintViolationWithTemplate(errorMessage);
            for (String field : fields) {
                constraintViolationBuilder.addNode(field).addConstraintViolation();
            }
        }
        return valid;
    }

    @Target({ElementType.TYPE, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Constraint(validatedBy = PasswordsValidator.class)
    public @interface PasswordsEqual {
        String DEFAULT_MESSAGE = "Passwords must be the same";

        String message() default DEFAULT_MESSAGE;

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        String[] fields() default {};

    }

}

