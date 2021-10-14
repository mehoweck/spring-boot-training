package pl.effectivedev.articles.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MinNumWordsValidator.class)
public @interface MinNumWords {
    String message() default "Not enough words";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int value() default 5;

}
