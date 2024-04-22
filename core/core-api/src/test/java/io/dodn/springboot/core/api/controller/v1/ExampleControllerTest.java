package io.dodn.springboot.core.api.controller.v1;

import io.dodn.springboot.core.api.board.v1.BoardController;
import io.dodn.springboot.core.domain.board.BoardService;
import io.dodn.springboot.test.api.RestDocsTest;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class ExampleControllerTest extends RestDocsTest {

    private BoardService boardService;

    private BoardController controller;

    @BeforeEach
    public void setUp() {
        boardService = mock(BoardService.class);
        controller = new BoardController(boardService);
        mockMvc = mockController(controller);
    }

//    @Test
//    public void exampleGet() {
//        when(exampleService.processExample(any())).thenReturn(new ExampleResult("BYE"));
//
//        given().contentType(ContentType.JSON)
//                .queryParam("exampleParam", "HELLO_PARAM")
//                .get("/get/{exampleValue}", "HELLO_PATH")
//                .then()
//                .status(HttpStatus.OK)
//                .apply(
//                        document(
//                                "exampleGet",
//                                requestPreprocessor(),
//                                responsePreprocessor(),
//                                pathParameters(
//                                        parameterWithName("exampleValue")
//                                                .description("ExampleValue")),
//                                queryParameters(
//                                        parameterWithName("exampleParam")
//                                                .description("ExampleParam")),
//                                responseFields(
//                                        fieldWithPath("result")
//                                                .type(JsonFieldType.STRING)
//                                                .description("ResultType"),
//                                        fieldWithPath("data.result")
//                                                .type(JsonFieldType.STRING)
//                                                .description("Result Date"),
//                                        fieldWithPath("error")
//                                                .type(JsonFieldType.NULL)
//                                                .ignored())));
//    }
//
//    @Test
//    public void examplePost() {
//        when(exampleService.processExample(any())).thenReturn(new ExampleResult("BYE"));
//
//        given().contentType(ContentType.JSON)
//                .body(new ExampleRequestDto("HELLO_BODY"))
//                .post("/post")
//                .then()
//                .status(HttpStatus.OK)
//                .apply(
//                        document(
//                                "examplePost",
//                                requestPreprocessor(),
//                                responsePreprocessor(),
//                                requestFields(
//                                        fieldWithPath("data")
//                                                .type(JsonFieldType.STRING)
//                                                .description("ExampleBody Data Field")),
//                                responseFields(
//                                        fieldWithPath("result")
//                                                .type(JsonFieldType.STRING)
//                                                .description("ResultType"),
//                                        fieldWithPath("data.result")
//                                                .type(JsonFieldType.STRING)
//                                                .description("Result Date"),
//                                        fieldWithPath("error")
//                                                .type(JsonFieldType.STRING)
//                                                .ignored())));
//    }

}
