package pl.effectivedev.articles.db;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Builder
@EqualsAndHashCode(
        onlyExplicitlyIncluded = true
)
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ARTICLE")
public class ArticleEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Include
    private String id;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String title;
    private String content;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String author;

    @Transient
    private String notForDb;

    @OneToMany(
            targetEntity = SourceEntity.class,
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ID", nullable = false)
    private Set<SourceEntity> sources;

//    [Article] <---[article_id, tag_id]---> [Tag]

//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(
//            name = "ARTICLE_TAGS",
//            joinColumns = @JoinColumn(name = "article_id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id")
//    )
//    private Set<String> tags;
//
//    @ManyToMany(mappedBy = "tags")
//    private Set<ArticleEntity> articles;
}
