package pl.effectivedev.articles.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class Article {
    private String title;
    private String content;
    private String author;
}
