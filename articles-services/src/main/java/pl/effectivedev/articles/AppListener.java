package pl.effectivedev.articles;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.effectivedev.articles.domain.ArticlesService;
import pl.effectivedev.articles.domain.ArticlesStorage;
import pl.effectivedev.articles.domain.model.Article;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppListener implements ApplicationListener {

    private final ArticlesStorage articlesStorage;
    private final ArticlesService articlesService;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            log.info("App Ready");

            var article = Article.builder()
                    .title("Some article")
                    .content("Lorem ipsum...")
                    .author("lmonkiewicz")
                            .build();

            log.info("### Article ### {}", articlesService.formatArticle(article));

            log.info("Storage: {}", articlesStorage);
        }
    }
}
