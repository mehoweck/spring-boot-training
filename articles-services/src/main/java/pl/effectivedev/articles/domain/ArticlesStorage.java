package pl.effectivedev.articles.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pl.effectivedev.articles.db.ArticleEntity;
import pl.effectivedev.articles.db.ArticleRepository;
import pl.effectivedev.articles.db.SourceEntity;
import pl.effectivedev.articles.domain.exception.ArticleNotFound;
import pl.effectivedev.articles.domain.model.Article;
import pl.effectivedev.articles.domain.model.ArticleId;
import pl.effectivedev.articles.domain.model.Sources;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;


import java.util.*;
import java.util.stream.Collectors;

@Component
//@Scope("prototype")
@Slf4j
@RequiredArgsConstructor
public class ArticlesStorage {

    private final ArticleRepository articleRepository;

//    private Map<ArticleId, Article> data = new HashMap<>();

    public ArticleId create(Article article) {
        var id = ArticleId.generate();
//        data.put(id, article);
        articleRepository.save(toEntity(id, article));
        log.info("Created: {}", id.asString());
        return id;
    }

    private ArticleEntity toEntity(ArticleId id, Article article) {
        var sources = new HashSet<SourceEntity>();
        if (article.getSources() != null) {
            if (article.getSources().getArticles() != null) {
                article.getSources().getArticles().forEach(src -> sources.add(SourceEntity.builder().content(src).type(SourceEntity.SourceType.ARTICLE).build()));
            }
            if (article.getSources().getWebLinks() != null) {
                article.getSources().getWebLinks().forEach(src -> sources.add(new SourceEntity(null, src, SourceEntity.SourceType.WEB)));
            }
            if (article.getSources().getNotes() != null) {
                article.getSources().getNotes().forEach(src -> sources.add(new SourceEntity(null, src, SourceEntity.SourceType.NOTE)));
            }
        }

        return ArticleEntity.builder()
                .author(article.getAuthor())
                .title(article.getTitle())
                .content(article.getContent())
                .id(id.asString())
                .sources(sources)
                .build();
    }
    // mapstruct
    private Article fromEntity(ArticleEntity entity) {
        var sources = Sources.builder();

        entity.getSources().stream()
                .filter(src -> src.getType() == SourceEntity.SourceType.ARTICLE)
                .forEach(src -> sources.article(src.getContent()));
        entity.getSources().stream()
                .filter(src -> src.getType() == SourceEntity.SourceType.WEB)
                .forEach(src -> sources.webLink(src.getContent()));
        entity.getSources().stream()
                .filter(src -> src.getType() == SourceEntity.SourceType.NOTE)
                .forEach(src -> sources.note(src.getContent()));

        return Article.builder()
                .content(entity.getContent())
                .author(entity.getAuthor())
                .title(entity.getTitle())
                .sources(sources.build())
                .build();
    }

    public void update(ArticleId id, Article article) {
        if (exists(id)) {
//            data.put(id, article);
            articleRepository.save(toEntity(id, article));
        } else {
            throw new ArticleNotFound(id);
        }
    }

    public List<Article> find(String title, String author) {
//        var articles = articleRepository.findAll();

//        var example = ArticleEntity.builder()
//                .title(title)
//                .author(author)
//                .build();

//        var entityExample = Example.of(example,
//                ExampleMatcher.matchingAll()
//                        .withMatcher("author", startsWith())
//                        .withMatcher("title", contains())
//                        .withIgnoreCase("title")
//                );
//        var articles = articleRepository.findAll(entityExample, Sort.by(Sort.Order.asc("title"), Sort.Order.desc("author")));
//        var articles = articleRepository.findAll(entityExample, PageRequest.of(1, 1)).getContent();

        var articles = articleRepository.findByAuthor(author);

        return articles.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
//        return new ArrayList<>(data.values());
    }

    public Article get(ArticleId id) {
        return articleRepository.findById(id.asString())
                .map(this::fromEntity)
                .orElseThrow(() -> new ArticleNotFound(id));
//        if (exists(id)) {
//            return data.get(id);
//        } else {
//            throw new ArticleNotFound(id);
//        }
    }

    public boolean exists(ArticleId id) {
        return articleRepository.existsById(id.asString());
//        return data.containsKey(id);
    }

    public void delete(ArticleId id) {
        if (exists(id)) {
//            data.remove(id);
            articleRepository.deleteById(id.asString());
        } else {
            throw new ArticleNotFound(id);
        }
    }
}
