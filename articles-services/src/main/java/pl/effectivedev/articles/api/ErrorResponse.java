package pl.effectivedev.articles.api;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class ErrorResponse {
    private String message;
    private String code;

    @Builder.Default
    private String traceId = UUID.randomUUID().toString();
}
