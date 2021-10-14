package pl.effectivedev.articles.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinNumWordsValidator implements ConstraintValidator<MinNumWords, String> {
    private int numWords;

    @Override
    public void initialize(MinNumWords constraintAnnotation) {
        this.numWords = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            var splitted = value.split(" ");
            return splitted.length >= numWords;
        }
        return true;
    }
}
