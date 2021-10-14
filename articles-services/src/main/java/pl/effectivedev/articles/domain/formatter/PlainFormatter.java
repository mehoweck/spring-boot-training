package pl.effectivedev.articles.domain.formatter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.effectivedev.articles.domain.model.Article;

//@Component
//@Qualifier("plainFormatter")
public class PlainFormatter implements ArticleFormatter {

    @Override
    public String format(Article article) {
        return String.format("%s - %s", article.getTitle(), article.getAuthor());
    }

    @Override
    public FormatType getFormatType() {
        return FormatType.PLAIN;
    }
}
