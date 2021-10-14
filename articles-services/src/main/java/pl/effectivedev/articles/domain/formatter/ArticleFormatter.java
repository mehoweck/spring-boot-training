package pl.effectivedev.articles.domain.formatter;

import pl.effectivedev.articles.domain.model.Article;

public interface ArticleFormatter {

    String format(Article article);

    FormatType getFormatType();

    enum FormatType {
        TITLE,
        PLAIN,
        FULL;
    }
}
