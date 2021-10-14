package pl.effectivedev.articles.domain.exception;

public class ArticleFormatterNotFound extends RuntimeException {

    public ArticleFormatterNotFound() {
        super("Article formatter not found");
    }
}
