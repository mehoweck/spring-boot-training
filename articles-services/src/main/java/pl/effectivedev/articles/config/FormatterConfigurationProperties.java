package pl.effectivedev.articles.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import pl.effectivedev.articles.domain.formatter.ArticleFormatter;

@Data
@ConfigurationProperties(prefix = "articles.formatters")
public class FormatterConfigurationProperties {
    private boolean plain = true;
    private boolean full = true;
    private String fullPrefix = "PREFIX";
    private ArticleFormatter.FormatType defaultFormatter = ArticleFormatter.FormatType.FULL;
}
