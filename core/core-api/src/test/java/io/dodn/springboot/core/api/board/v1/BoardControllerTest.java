package io.dodn.springboot.core.api.board.v1;

import io.dodn.springboot.core.domain.board.Board;
import io.dodn.springboot.core.domain.board.BoardService;
import io.dodn.springboot.test.api.RestDocsTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;

import static io.dodn.springboot.test.api.RestDocsUtils.requestPreprocessor;
import static io.dodn.springboot.test.api.RestDocsUtils.responsePreprocessor;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

class BoardControllerTest extends RestDocsTest {

    private BoardService boardService;
    private BoardController boardController;

    @BeforeEach
    public void setUp() {
        boardService = mock(BoardService.class);
        boardController = new BoardController(boardService);
        mockMvc = mockController(boardController);
    }

    @Test
    public void get_board() {
        Board board = createBoard();
        BDDMockito.given(boardService.getBoard(anyLong())).willReturn(board);

        given().contentType(ContentType.JSON)
                .get("/api/v1/boards/{boardId}", "1")
                .then()
                .status(HttpStatus.OK)
                .apply(
                        document("board-get-one", requestPreprocessor(), responsePreprocessor(),
                                pathParameters(parameterWithName("boardId").description("게시글 ID")),
                                responseFields(
                                        fieldWithPath("result").type(JsonFieldType.STRING).description("성공/실패"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("게시글 ID"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("게시글 제목"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("게시글 내용"),
                                        fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("작성자 ID")))
                );
    }

    private Board createBoard() {
        return Board.builder()
                .id(1L)
                .title("Test Title")
                .content("Test Content")
                .userId(1L)
                .build();
    }
}