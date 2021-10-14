package pl.effectivedev.bff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import pl.effectivedev.bff.openapi.articles.model.SourcesDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * ArticleDto
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-10-13T11:48:04.557304+02:00[Europe/Warsaw]")


public class ArticleDto implements Serializable  {
  private static final long serialVersionUID = 1L;

  @JsonProperty("author")
  private String author = null;

  @JsonProperty("content")
  private String content = null;

  @JsonProperty("creator")
  private String creator = null;

  @JsonProperty("sources")
  private SourcesDto sources = null;

  @JsonProperty("title")
  private String title = null;

  public ArticleDto author(String author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   * @return author
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public ArticleDto content(String content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public ArticleDto creator(String creator) {
    this.creator = creator;
    return this;
  }

  /**
   * Get creator
   * @return creator
   **/
  @Schema(required = true, description = "")
    public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public ArticleDto sources(SourcesDto sources) {
    this.sources = sources;
    return this;
  }

  /**
   * Get sources
   * @return sources
   **/
  @Schema(description = "")
  
    @Valid
    public SourcesDto getSources() {
    return sources;
  }

  public void setSources(SourcesDto sources) {
    this.sources = sources;
  }

  public ArticleDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArticleDto article = (ArticleDto) o;
    return Objects.equals(this.author, article.author) &&
        Objects.equals(this.content, article.content) &&
        Objects.equals(this.creator, article.creator) &&
        Objects.equals(this.sources, article.sources) &&
        Objects.equals(this.title, article.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(author, content, creator, sources, title);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArticleDto {\n");
    
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    creator: ").append(toIndentedString(creator)).append("\n");
    sb.append("    sources: ").append(toIndentedString(sources)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
