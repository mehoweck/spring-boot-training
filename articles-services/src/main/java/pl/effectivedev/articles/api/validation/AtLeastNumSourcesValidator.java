package pl.effectivedev.articles.api.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.effectivedev.articles.domain.ArticlesStorage;
import pl.effectivedev.articles.domain.model.ArticleId;
import pl.effectivedev.articles.domain.model.Sources;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class AtLeastNumSourcesValidator implements ConstraintValidator<AtLeastNumSources, Sources> {
    private final ArticlesStorage articlesStorage;
    private int numSources;

    @Override
    public void initialize(AtLeastNumSources constraintAnnotation) {
        this.numSources = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Sources value, ConstraintValidatorContext context) {
        if (value != null) {
            var count = 0;

            if (value.getArticles() != null) {
                count += value.getArticles().size();
                var allExist = value.getArticles().stream()
                        .allMatch(id -> articlesStorage.exists(ArticleId.fromString(id)));

                if (!allExist) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("Article must exist")
                            .addPropertyNode("articles")
                            .addConstraintViolation();
                    return false;
                }
            }
            if (value.getNotes() != null) {
                count += value.getNotes().size();
            }
            if (value.getWebLinks() != null) {
                count += value.getWebLinks().size();

                var allURLs = value.getWebLinks().stream()
                        .allMatch(link -> link.startsWith("http://") || link.startsWith("https://"));
                if (!allURLs) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("WebLink must be a valid URL")
                            .addPropertyNode("webLinks")
                            .addConstraintViolation();
                    return false;
                }
            }

            return count >= numSources;
        }
        return true;
    }
}
