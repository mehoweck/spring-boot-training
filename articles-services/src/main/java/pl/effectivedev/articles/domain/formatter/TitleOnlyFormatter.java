package pl.effectivedev.articles.domain.formatter;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.effectivedev.articles.domain.model.Article;

//@Component
//@Primary
public class TitleOnlyFormatter implements ArticleFormatter{
    @Override
    public String format(Article article) {
        return article.getTitle();
    }

    @Override
    public FormatType getFormatType() {
        return FormatType.TITLE;
    }
}
