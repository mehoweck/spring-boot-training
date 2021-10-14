package pl.effectivedev.articles.domain.model;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode
public class ArticleId {
    private final String id;

    private ArticleId(String id) {
        this.id = id;
    }

    public static ArticleId fromString(String id) {
        // dowolna walidacja
        return new ArticleId(id);
    }

    public static ArticleId generate() {
        return fromString(UUID.randomUUID().toString());
    }

    public String asString() {
        return id;
    }
}
