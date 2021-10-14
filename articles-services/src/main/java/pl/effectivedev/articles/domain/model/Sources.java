package pl.effectivedev.articles.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
@Getter
public class Sources {
    @Singular
    private List<String> notes;
    @Singular
    private List<String> articles;
    @Singular
    private List<String> webLinks;
}
