package pl.effectivedev.bff.openfeign;


import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class ArticlesClientConfiguration {

    @Bean
    public ErrorDecoder errorDecoder(ObjectMapper objectMapper) {
        return new ArticlesServiceErrorDecoder(objectMapper);
    }

    @Bean
    public RequestInterceptor xuserInterceptor() {
        return request -> {
            var hasUser = request.request().headers().containsKey("x-user");
            if (!hasUser) {
                request.header("x-user", "auto-bff-service");
            }
        };
    }
}
