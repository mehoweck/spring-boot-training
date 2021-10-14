package pl.effectivedev.articles.domain;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.effectivedev.articles.domain.exception.ArticleFormatterNotFound;
import pl.effectivedev.articles.domain.formatter.ArticleFormatter;
import pl.effectivedev.articles.domain.model.Article;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticlesService {

//    @Autowired
    private final ArticlesStorage articlesStorage;
    private final Set<ArticleFormatter> articleFormatters;

//    public ArticlesService(ArticlesStorage articlesStorage, @Qualifier("plainFormatter") ArticleFormatter articleFormatter) {
//        this.articlesStorage = articlesStorage;
//        this.articleFormatter = articleFormatter;
//    }

    @PostConstruct
    public void init() {
        log.info("Initialized with: {}", articleFormatters);
    }

    @PreDestroy
    public void onDestroy() {
        log.info("Destroying");
    }

    public String formatArticle(Article article) {
        var articleFormatter = articleFormatters.stream()
                .filter(formatter -> formatter.getFormatType() == ArticleFormatter.FormatType.PLAIN)
                .findFirst()
                .orElseThrow(ArticleFormatterNotFound::new);

        return articleFormatter.format(article);
    }

//    public ArticlesService(ArticlesStorage articlesStorage) {
//        this.articlesStorage = articlesStorage;
//    }

    // 1. ArticlesService
    // 2. ArticlesStorage
    // 3. ArticlesService.articlestStorage = ArticlesStorage
}