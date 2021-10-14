package pl.effectivedev.articles.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.effectivedev.articles.api.validation.AtLeastNumSources;
import pl.effectivedev.articles.api.validation.MinNumWords;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Builder
@ToString
@Getter
public class Article {
    @NotBlank(message = "Title cannot be empty", groups = {Create.class, Update.class})
    @MinNumWords(value = 3, groups = {Create.class, Update.class})
    private String title;
    @NotNull(groups = {Create.class, Update.class})
    private String content;
    @NotBlank(groups = {Create.class, Update.class})
    private String author;
    @Setter
    @Null(groups = {Create.class})
    @NotBlank(groups = {Update.class})
    private String creator;

    @AtLeastNumSources(value = 3, groups = {Create.class, Update.class})
    private Sources sources;

    public interface Create {};
    public interface Update {};
}
