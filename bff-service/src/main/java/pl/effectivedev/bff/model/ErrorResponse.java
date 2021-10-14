package pl.effectivedev.bff.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Jacksonized
@Builder
@Getter
public class ErrorResponse {
    @Singular
    private List<String> messages;
    private String code;
    private String traceId;
}
