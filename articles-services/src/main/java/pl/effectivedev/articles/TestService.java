package pl.effectivedev.articles;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.effectivedev.articles.domain.ArticlesService;
import pl.effectivedev.articles.domain.model.Article;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestService {

    private final ArticlesService articlesService;


    // Quartz <- DB
    // https://dzone.com/articles/managing-quartz-using-spring-actuator


    // ShedLock

//    --> [Async.save -> [ArticleService.save]]
//    @Scheduled(cron = "0 0/30 * ? * *")
//    @Scheduled(cron = "${articles.create.cron}")
    @Scheduled(fixedRate = 5000)
    @Async
    public void createArticle() throws ExecutionException, InterruptedException, TimeoutException {
        var article = Article.builder()
                .title("Some title #"+ ThreadLocalRandom.current().nextInt(0, 1000))
                .author("lmonkiewicz")
                .content("Lorem ipsum...")
                .build();

//        var id = articlesService.save(article).get(1, TimeUnit.SECONDS);
        var id = articlesService.save(article, "scheduler");
        log.info("Created article {}: {}", id.asString(), articlesService.formatArticle(article));
    }

    @Scheduled(fixedRate = 17000)
    public void listArticles() {
        var articles = articlesService.findArticles(null, null);

        log.info("Number of articles: {}", articles.size());
    }
}
