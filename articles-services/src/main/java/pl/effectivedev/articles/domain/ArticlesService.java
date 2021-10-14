package pl.effectivedev.articles.domain;


import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.effectivedev.articles.config.FormatterConfigurationProperties;
import pl.effectivedev.articles.config.MetricOperations;
import pl.effectivedev.articles.domain.exception.ArticleFormatterNotFound;
import pl.effectivedev.articles.domain.formatter.ArticleFormatter;
import pl.effectivedev.articles.domain.model.Article;
import pl.effectivedev.articles.domain.model.ArticleId;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
//@Transactional
public class ArticlesService {

    // --> [Transactional.create -> [ArticleService.create]]


//    @Autowired
    private final ArticlesStorage articlesStorage;
    private final Set<ArticleFormatter> articleFormatters;
    private final FormatterConfigurationProperties configurationProperties;
    private final MetricOperations metricOperations;


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
    @Transactional(
            propagation = Propagation.REQUIRES_NEW,
            rollbackFor = {Exception.class},
            timeout = 30000//,
//            readOnly = true
    )
//    @Modifying
    public ArticleId save(Article article, String creator) {
        metricOperations.increment("articles_save_count",  Tags.of("creator", creator));
        var start = System.currentTimeMillis();
        article.setCreator(creator);
        final ArticleId id = articlesStorage.create(article);

        var duration = System.currentTimeMillis() - start;
        metricOperations.recordDuration("articles_save", Tags.of("creator", creator), duration, TimeUnit.MILLISECONDS);

        return id;
    }

    @Timed("articles_find")
    public List<Article> findArticles(String title, String author) {
        final List<Article> articleList = articlesStorage.find(title, author);

        metricOperations.setArticleCount(articleList.size());
        return articleList;
    }

    public Article getArticle(ArticleId id) {
        return articlesStorage.get(id);
    }

    public void update(ArticleId id, Article article) {
        articlesStorage.update(id, article);
    }

    public void delete(ArticleId id) {
        articlesStorage.delete(id);
    }

    public String printArticle(ArticleId id) {
        var article = articlesStorage.get(id);
        return formatArticle(article);
    }

//    public ArticlesService(ArticlesStorage articlesStorage) {
//        this.articlesStorage = articlesStorage;
//    }

    // 1. ArticlesService
    // 2. ArticlesStorage
    // 3. ArticlesService.articlestStorage = ArticlesStorage
}
