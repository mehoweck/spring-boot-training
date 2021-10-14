package pl.effectivedev.bff.openfeign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.effectivedev.bff.model.Article;

import java.net.URI;
import java.util.List;

@FeignClient(
        name = "articlesService",
        contextId = "articlesClient",
        url = "${articles.url}/articles",
        configuration = ArticlesClientConfiguration.class
)
public interface ArticlesClient {

    @GetMapping
    List<Article> getArticles(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author);


    @PostMapping
    String createArticle(@RequestBody Article article, @RequestHeader("x-user") String creator);
}
