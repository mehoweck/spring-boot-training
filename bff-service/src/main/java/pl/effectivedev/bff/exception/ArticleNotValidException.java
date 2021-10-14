package pl.effectivedev.bff.exception;

import pl.effectivedev.bff.model.Article;

public class ArticleNotValidException extends RuntimeException {
    public ArticleNotValidException(Article article) {
        super("Article is not valid: "+article);
    }
}
