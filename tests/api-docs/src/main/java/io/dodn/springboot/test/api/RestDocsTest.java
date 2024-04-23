package io.dodn.springboot.test.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@Tag("restdocs")
@ExtendWith(RestDocumentationExtension.class)
public abstract class RestDocsTest {

    protected MockMvcRequestSpecification mockMvc;

    private RestDocumentationContextProvider restDocumentation;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        this.restDocumentation = restDocumentation;
    }

    protected MockMvcRequestSpecification when() {
        return mockMvc.contentType(ContentType.JSON).log().everything(true);
    }

    protected MockMvcRequestSpecification mockController(Object controller) {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        MockMvc mockMvc = createMockMvc(controller);
        RestAssuredMockMvcConfig config = RestAssuredMockMvcConfig.config()
                .logConfig(LogConfig.logConfig()
                        .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)
                        .enablePrettyPrinting(true));
        return RestAssuredMockMvc.given().mockMvc(mockMvc).config(config);
    }

    private MockMvc createMockMvc(Object controller) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper());

        return MockMvcBuilders.standaloneSetup(controller)
            .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
            .setMessageConverters(converter)
            .build();
    }

    private ObjectMapper objectMapper() {
        return new ObjectMapper().findAndRegisterModules()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
    }

}
