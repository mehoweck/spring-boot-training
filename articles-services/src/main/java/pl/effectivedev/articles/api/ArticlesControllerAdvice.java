package pl.effectivedev.articles.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.effectivedev.articles.domain.exception.ArticleNotFound;

//@RestControllerAdvice(basePackageClasses = ArticlesController.class)
@RestControllerAdvice
@Slf4j
public class ArticlesControllerAdvice {


    @ExceptionHandler({ArticleNotFound.class})
    public ResponseEntity<ErrorResponse> handleArticleNotFound(ArticleNotFound ex) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .code("40404")
                .build();

        log.error("[{}] ArticleNotFound", errorResponse.getTraceId(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }
}
