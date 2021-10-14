package pl.effectivedev.articles.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AtLeastNumSourcesValidator.class)
public @interface AtLeastNumSources {
    String message() default "Not enough sources";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int value() default 5;
}
