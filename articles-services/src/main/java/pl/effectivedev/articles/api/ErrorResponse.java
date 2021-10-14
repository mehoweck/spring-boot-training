package pl.effectivedev.articles.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class ErrorResponse {
    @Singular
    private List<String> messages;
    private String code;

    @Builder.Default
    private String traceId = UUID.randomUUID().toString();
}
