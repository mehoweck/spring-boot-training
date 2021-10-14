package pl.effectivedev.articles.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {

    List<ArticleEntity> findByAuthorStartingWithAndTitleContaining(String author, String title);
    List<ArticleEntity> findByAuthorStartingWithOrTitleContaining(String author, String title);
    List<ArticleEntity> findByAuthorStartingWithAndTitleNotContaining(String author, String title);
    List<ArticleEntity> findByAuthorStartingWithOrTitleNotContaining(String author, String title);

    List<ArticleEntity> findByAuthor(String author);
    List<ArticleEntity> findByAuthor(String author, Sort sort);
    Page<ArticleEntity> findByAuthor(String author, Pageable pageable);

    // JPQL
    @Query("SELECT a FROM ArticleEntity a WHERE a.author = ?1")
    List<ArticleEntity> findByAuthorName(String author);

    @Query("SELECT a FROM ArticleEntity a WHERE a.author = ?1")
    Page<ArticleEntity> findByAuthorName(String author, Pageable pageable);

    // Native
    @Query(value = "SELECT * FROM ARTICLE WHERE author = ?1", nativeQuery = true)
    List<ArticleEntity> findNativeByAuthorName(String author);

    @Query(value = "SELECT * FROM ARTICLE WHERE author = ?1",
            countQuery = "SELECT count(*) FROM ARTICLE WHERE author = ?1",
            nativeQuery = true)
    Page<ArticleEntity> findNativeByAuthorName(String author, Pageable pageable);


}
