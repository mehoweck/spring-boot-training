package pl.effectivedev.articles.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.effectivedev.articles.domain.exception.ArticleNotFound;
import pl.effectivedev.articles.domain.model.Article;
import pl.effectivedev.articles.domain.model.ArticleId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
//@Scope("prototype")
public class ArticlesStorage {

    private Map<ArticleId, Article> data = new HashMap<>();

    public ArticleId create(Article article) {
        var id = ArticleId.generate();
        data.put(id, article);
        return id;
    }

    public void update(ArticleId id, Article article) {
        if (exists(id)) {
            data.put(id, article);
        } else {
            throw new ArticleNotFound(id);
        }
    }

    public List<Article> find() {
        return new ArrayList<>(data.values());
    }

    public Article get(ArticleId id) {
        if (exists(id)) {
            return data.get(id);
        } else {
            throw new ArticleNotFound(id);
        }
    }

    public boolean exists(ArticleId id) {
        return data.containsKey(id);
    }

    public void delete(ArticleId id) {
        if (exists(id)) {
            data.remove(id);
        } else {
            throw new ArticleNotFound(id);
        }
    }
}
