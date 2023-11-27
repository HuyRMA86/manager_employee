package fa.training.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = GenderValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sex {
    String message() default "{Phone}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
