package pl.effectivedev.bff.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@ToString
@Getter
public class Article {
    private String title;
    private String content;
    private String author;
    @Setter
    private String creator;

    private Sources sources;
}
