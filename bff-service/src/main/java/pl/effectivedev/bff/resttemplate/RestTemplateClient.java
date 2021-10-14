package pl.effectivedev.bff.resttemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.effectivedev.bff.exception.ArticleNotValidException;
import pl.effectivedev.bff.model.Article;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class RestTemplateClient {

    public static final String URL = "http://localhost:8080/articles";
    private final RestTemplate restTemplate;

    public String createArticle(Article article) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-user", "bff");

        var entity = new RequestEntity<Article>(
          article, headers, HttpMethod.POST, URI.create(URL)
        );

        var response = restTemplate.exchange(entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new ArticleNotValidException(article);
        }

    }

    public List<Article> getArticles() {

        var requestUrl = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("title", "cokolwiek")
                .toUriString();

        var articles = restTemplate.getForObject(requestUrl, Article[].class);

        return List.of(articles);
    }
}
