package pl.effectivedev.bff.openfeign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import pl.effectivedev.bff.exception.ArticleNotValidException;
import pl.effectivedev.bff.model.ErrorResponse;

@RequiredArgsConstructor
public class ArticlesServiceErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper;
    private final ErrorDecoder errorDecoder = new Default();

    @SneakyThrows
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.BAD_REQUEST.value()) {
            var body = response.body();
            var error = objectMapper.readValue(body.asInputStream(), ErrorResponse.class);

            if (error.getCode().equals("40400")) {
                return new ArticleNotValidException(null);
            }
        }
//        return new RuntimeException(response.reason());
        return errorDecoder.decode(methodKey, response);

    }
}
