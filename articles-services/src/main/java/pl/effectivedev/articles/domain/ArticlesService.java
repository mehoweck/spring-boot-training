package pl.effectivedev.articles.domain;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.effectivedev.articles.config.FormatterConfigurationProperties;
import pl.effectivedev.articles.domain.exception.ArticleFormatterNotFound;
import pl.effectivedev.articles.domain.formatter.ArticleFormatter;
import pl.effectivedev.articles.domain.model.Article;
import pl.effectivedev.articles.domain.model.ArticleId;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticlesService {

//    @Autowired
    private final ArticlesStorage articlesStorage;
    private final Set<ArticleFormatter> articleFormatters;
    private final FormatterConfigurationProperties configurationProperties;


//    @Value("${articles.formatters.defaultFormatter:FULL}")
//    private ArticleFormatter.FormatType defaultFormatter;

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
                .filter(formatter -> formatter.getFormatType() == configurationProperties.getDefaultFormatter())
                .findFirst()
                .orElseThrow(ArticleFormatterNotFound::new);

        return articleFormatter.format(article);
    }

//    @Async
//    public CompletableFuture<ArticleId> save(Article article) {
//        return CompletableFuture.completedFuture(articlesStorage.create(article));
//
//    }
    public ArticleId save(Article article) {
        return articlesStorage.create(article);
    }

    public List<Article> findArticles() {
        return articlesStorage.find();
    }

//    public ArticlesService(ArticlesStorage articlesStorage) {
//        this.articlesStorage = articlesStorage;
//    }

    // 1. ArticlesService
    // 2. ArticlesStorage
    // 3. ArticlesService.articlestStorage = ArticlesStorage
}
