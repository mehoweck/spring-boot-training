package pl.effectivedev.articles.domain.formatter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.effectivedev.articles.domain.model.Article;

//@Component
public class FullFormatter implements ArticleFormatter {

    private String prefix;

    public FullFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(Article article) {
        return String.format("%s %s - %s: %s", prefix, article.getTitle(), article.getAuthor(), article.getContent());
    }

    @Override
    public FormatType getFormatType() {
        return FormatType.FULL;
    }
}
