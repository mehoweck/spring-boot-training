package pl.effectivedev.articles.db;


import lombok.*;

import javax.persistence.*;

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
@Table(name = "SOURCE")
public class SourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Include
    private String content;
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    private SourceType type;


    public enum SourceType {
        NOTE,
        WEB,
        ARTICLE;
    }
}
