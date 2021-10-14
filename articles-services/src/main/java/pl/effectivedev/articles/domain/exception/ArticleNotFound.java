package pl.effectivedev.articles.domain.exception;

import pl.effectivedev.articles.domain.model.ArticleId;

import static java.lang.String.format;

public class ArticleNotFound extends RuntimeException {
    public ArticleNotFound(ArticleId id) {
        super(format("Article with id '%s' not found", id.asString()));
    }
}
