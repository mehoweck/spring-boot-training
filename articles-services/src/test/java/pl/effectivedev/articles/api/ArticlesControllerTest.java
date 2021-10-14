package pl.effectivedev.articles.api;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.effectivedev.articles.domain.ArticlesStorage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ArticlesControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    private ArticlesStorage articlesStorage;

//    @BeforeEach
//    private void beforeAll() {
//        MockMvcBuilders.standaloneSetup(ArticlesController.class)
//                .
//    }

    @Test
    public void shouldReturnArticlesList() throws Exception {
        mockMvc.perform(get("/articles")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title").value(Matchers.matchesPattern(".+ #\\d+")))
                .andExpect(jsonPath("$[0].author").value(Matchers.anyOf(Matchers.equalTo("lmonkiewicz"), Matchers.equalTo("tester"))));
    }

    @Test
    public void shouldCreateArticle() throws Exception{
        mockMvc.perform(post("/articles")
                        .content(loadJson("article.json"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-user", "junit")
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, Matchers.matchesPattern("/articles/.{36}")));
    }

    @ParameterizedTest
    @MethodSource("validationData")
    public void shouldValidateOnCreateArticle(String json, String responseCode, String responseMessage) throws Exception{
        mockMvc.perform(post("/articles")
                        .content(loadJson(json))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-user", "junit")
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(responseCode))
                .andExpect(jsonPath("$.messages[0]").value(responseMessage));
    }

    public static Stream<Arguments> validationData() {
        return Stream.of(
                Arguments.of("article-emptyTitle.json", "40400", "Field value error in 'title': Not enough words"),
                Arguments.of("article-notEnoughWordsInTitle.json", "40400", "Field value error in 'title': Not enough words")
//                Arguments.of("article-notNullCreator.json", "40400", "Title cannot be empty"),
//                Arguments.of("article-emptyTitle.json", "40400", "Title cannot be empty"),
//                Arguments.of("article-emptyTitle.json", "40400", "Title cannot be empty"),
//                Arguments.of("article-emptyTitle.json", "40400", "Title cannot be empty")
        );
    }


    private String loadJson(String path) throws IOException {
        var stream = getClass().getResourceAsStream(path);
        return IOUtils.toString(stream, StandardCharsets.UTF_8);
    }

}