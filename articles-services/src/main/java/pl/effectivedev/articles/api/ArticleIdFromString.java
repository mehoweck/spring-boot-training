package pl.effectivedev.articles.api;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.effectivedev.articles.domain.model.ArticleId;

@Component
public class ArticleIdFromString implements Converter<String, ArticleId> {

    @Override
    public ArticleId convert(String source) {
        return ArticleId.fromString(source);
    }
}
