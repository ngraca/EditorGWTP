package pt.scalablesolutions.client.validation;

import com.google.gwt.editor.client.EditorError;
import com.google.gwt.validation.client.impl.ConstraintViolationImpl;
import com.google.gwt.validation.client.impl.PathImpl;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViolationUtil {

    public static Set<ConstraintViolation<Object>> convertErrorsIntoViolations(Object obj, List<EditorError> errors) {
        Set<ConstraintViolation<Object>> violations = new HashSet<ConstraintViolation<Object>>();
        for (EditorError error : errors) {
            ConstraintViolationImpl.Builder<Object> builder = ConstraintViolationImpl.builder();
            String absolutePath = error.getAbsolutePath();
            PathImpl path = new PathImpl().append(absolutePath);
            builder.setPropertyPath(path);

            builder.setMessage(error.getMessage());
            builder.setInvalidValue(error.getValue());
            builder.setRootBean(obj);
            violations.add(builder.build());
        }
        return violations;
    }
}
