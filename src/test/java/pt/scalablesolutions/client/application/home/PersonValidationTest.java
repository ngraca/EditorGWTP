package pt.scalablesolutions.client.application.home;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Set;

public class PersonValidationTest {

    private Validator validator;

    @Before
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testPersonNameNull() {
        Person person = new Person();
        person.setAge(10);
        person.setPassword("123");
        person.setPasswordVerify("123");
        person.setBirthDate(new Date());

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        for (ConstraintViolation<Person> violation : violations) {
            Assert.assertThat(violation.getMessage(), Matchers.is("may not be null"));
        }
    }

    @Test
    public void testPersonNameTooShort() {
        Person person = new Person();
        person.setName("aa");
        person.setAge(10);
        person.setPassword("123");
        person.setPasswordVerify("123");
        person.setBirthDate(new Date());

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        for (ConstraintViolation<Person> violation : violations) {
            Assert.assertThat(violation.getMessage(), Matchers.is("Name must be at least 4 characters long."));
        }
    }

    @Test
    public void testDifferentPasswords() {
        Person person = new Person();
        person.setName("aaaa");
        person.setAge(10);
        person.setPassword("1234");
        person.setPasswordVerify("123");
        person.setBirthDate(new Date());

        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        for (ConstraintViolation<Person> violation : violations) {
            Assert.assertThat(violation.getMessage(), Matchers.is("Passwords must be the same"));
        }
    }
}
