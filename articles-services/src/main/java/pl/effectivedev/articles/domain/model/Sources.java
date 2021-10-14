package pl.effectivedev.articles.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
@Getter
public class Sources {
    private List<String> notes;
    private List<String> articles;
    private List<String> webLinks;
}
