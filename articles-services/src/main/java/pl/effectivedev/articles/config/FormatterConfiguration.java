package pl.effectivedev.articles.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pl.effectivedev.articles.domain.formatter.ArticleFormatter;
import pl.effectivedev.articles.domain.formatter.FullFormatter;
import pl.effectivedev.articles.domain.formatter.PlainFormatter;
import pl.effectivedev.articles.domain.formatter.TitleOnlyFormatter;

@Configuration
@EnableConfigurationProperties({FormatterConfigurationProperties.class})
@Profile("formatters")
public class FormatterConfiguration {

    @Bean
    @Qualifier("plainFormatter")
    @ConditionalOnProperty(prefix = "articles.formatters", name = "plain", matchIfMissing = true)
    @Profile("plainFormatter")
    public ArticleFormatter plainFormatter() {
        return new PlainFormatter();
    }

    @Bean
    @Primary
    @ConditionalOnProperty(prefix = "articles.formatters", name = "full", matchIfMissing = true)
    @Profile("plainFormatter")
    public ArticleFormatter fullFormatter(@Value("${articles.formatters.fullPrefix:PREFIX}") String prefix) {
        return new FullFormatter(prefix);
    }

    @Bean
    @ConditionalOnMissingBean
    public ArticleFormatter titleOnlyFormatter() {
        return new TitleOnlyFormatter();
    }
}
