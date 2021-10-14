package pl.effectivedev.articles.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.effectivedev.articles.domain.ArticlesService;
import pl.effectivedev.articles.domain.model.Article;
import pl.effectivedev.articles.domain.model.ArticleId;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/articles") // http://localhost:8080/articles
@RequiredArgsConstructor
@Slf4j
public class ArticlesController {

    private final ArticlesService articlesService;
    private final CurrentUser currentUser;

    // GET http://localhost:8080/articles?title=XXX&author=YYYY
    @GetMapping
    public List<Article> getArticles(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author) {
        return articlesService.findArticles(title, author);
    }

    // GET http://localhost:8080/articles/11111-1111-1111-111111-111111
    @GetMapping("/{id}")
    public Article getArticle(@PathVariable("id") ArticleId id) {
        return articlesService.getArticle(id);
    }
    // POST http://localhost:8080/articles
    // { ... }
    @PostMapping
    public ResponseEntity<String> createArticle(@Validated(Article.Create.class) @RequestBody Article article, @RequestHeader("x-user") String creator) {
        var id = articlesService.save(article, currentUser.getUser()).asString();

        return ResponseEntity
//                .status(201)
//                .contentType(MediaType.APPLICATION_JSON)
//                .header("dowolne", "wartosc")
                .created(URI.create("/articles/"+id))
                .body(id);
    }

    // PUT http://localhost:8080/articles/111111-1111-11111-11111-11111
    // { ... }
    @PutMapping("/{id}")
    public void updateArticle(@PathVariable("id") ArticleId id, @Validated(Article.Update.class) @RequestBody Article article) {
        articlesService.update(id, article);
    }

    // DELETE http://localhost:8080/articles/1111111-1111-1111-111111
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable("id") ArticleId id) {
        articlesService.delete(id);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadArticle(@PathVariable("id") ArticleId id) {
        var article = articlesService.printArticle(id);

        var resource = new InputStreamResource(new ByteArrayInputStream(article.getBytes(StandardCharsets.UTF_8)));
//        new FileSystemResource("/aaaaa.txt");
        var disposition = ContentDisposition.attachment()
                .filename("article.txt", StandardCharsets.UTF_8)
                .build();

        var headers = new HttpHeaders();
        headers.setContentDisposition(disposition);

//        var contentType = MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @PostMapping("/{id}/upload")
    public void upload(@PathVariable("id") ArticleId id, @RequestParam("file") MultipartFile file) {
        try {
            log.info("Upload of {} with bytes: {}", file.getOriginalFilename(), new String(file.getBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
