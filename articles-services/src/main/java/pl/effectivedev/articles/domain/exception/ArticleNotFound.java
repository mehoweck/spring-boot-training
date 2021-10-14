package pl.effectivedev.articles.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.effectivedev.articles.domain.model.ArticleId;

import static java.lang.String.format;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArticleNotFound extends RuntimeException {
    public ArticleNotFound(ArticleId id) {
        super(format("Article with id '%s' not found", id.asString()));
    }
}
