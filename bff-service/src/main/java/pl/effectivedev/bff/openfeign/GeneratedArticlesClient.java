package pl.effectivedev.bff.openfeign;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.openfeign.FeignClient;
import pl.effectivedev.bff.openapi.articles.ArticlesControllerApi;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@FeignClient(
        name = "articlesService",
        contextId = "generatedArticlesClient",
        url = "${articles.url}",
        configuration = ArticlesClientConfiguration.class
)
public interface GeneratedArticlesClient extends ArticlesControllerApi {

    @Override
    default Optional<HttpServletRequest> getRequest() { return Optional.empty(); }

    @Override
    default Optional<ObjectMapper> getObjectMapper() { return Optional.empty(); }
}
