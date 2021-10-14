package pl.effectivedev.bff;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.effectivedev.bff.model.Article;
import pl.effectivedev.bff.openapi.articles.model.ArticleDto;
import pl.effectivedev.bff.openfeign.ArticlesClient;
import pl.effectivedev.bff.openfeign.GeneratedArticlesClient;
import pl.effectivedev.bff.resttemplate.RestTemplateClient;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestService {


    private final RestTemplateClient restTemplateClient;
    private final ArticlesClient articlesClient;
    private final GeneratedArticlesClient generatedArticlesClient;
    // 1. RestTemplate
    // 2. OpenFeign
    // 3. Wygenerowany klient OpenFeign z OpenAPI


    @Scheduled(fixedRate = 5000)
    public void createArticle() {
//        var article = createGoodArticle();
        var article = createBadArticle();

//        var id = restTemplateClient.createArticle(article);
//        var id = articlesClient.createArticle(article, "bff-service");
        var id = generatedArticlesClient.createArticleUsingPOST( "bff-service", toDto(article)).getBody();
        log.info("Article created: {}", id);
    }

    private ArticleDto toDto(Article article) {
        return new ArticleDto()
                .title(article.getTitle())
                .content(article.getContent())
                .author(article.getAuthor());

    }

    @Scheduled(fixedRate = 13000)
    public void getArticles() {

//        var articles = restTemplateClient.getArticles();
//        var articles = articlesClient.getArticles(null, null);
        var articles = generatedArticlesClient.getArticlesUsingGET(null, null).getBody();

        log.info("Article list size: {}", articles.size());
    }

    private Article createGoodArticle() {
        return Article.builder()
                .title("Test article #"+ ThreadLocalRandom.current().nextInt(0, 1000))
                .content("Lorem ipsum...")
                .author("bff-service")
                .build();
    }

    private Article createBadArticle() {
        return Article.builder()
                .title("#"+ ThreadLocalRandom.current().nextInt(0, 1000))
                .content("Lorem ipsum...")
                .author("bff-service")
                .build();
    }
}
