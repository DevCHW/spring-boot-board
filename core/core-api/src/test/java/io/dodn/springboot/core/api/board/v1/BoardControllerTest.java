package io.dodn.springboot.core.api.board.v1;

import io.dodn.springboot.core.api.board.v1.request.CreateBoardRequest;
import io.dodn.springboot.core.domain.board.Board;
import io.dodn.springboot.core.domain.board.BoardCreateParam;
import io.dodn.springboot.core.domain.board.BoardService;
import io.dodn.springboot.test.api.RestDocsTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.time.LocalDateTime;

import static io.dodn.springboot.test.api.RestDocsUtils.requestPreprocessor;
import static io.dodn.springboot.test.api.RestDocsUtils.responsePreprocessor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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
    @DisplayName("게시글 조회")
    public void get_board() {
        Board board = createBoard();
        given(boardService.getBoard(anyLong())).willReturn(board);

        when().contentType(ContentType.JSON)
                .get("/api/v1/boards/{boardId}", "1")
                .then().log().all()
                .status(HttpStatus.OK)
                .apply(document("board-get-one",
                                requestPreprocessor(),
                                responsePreprocessor(),
                                pathParameters(
                                        parameterWithName("boardId").description("게시글 ID")
                                ),
                                responseFields(
                                        fieldWithPath("result").type(STRING).description("성공/실패 결과"),
                                        fieldWithPath("data").type(OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.id").type(NUMBER).description("게시글 ID"),
                                        fieldWithPath("data.title").type(STRING).description("게시글 제목"),
                                        fieldWithPath("data.content").type(STRING).description("게시글 내용"),
                                        fieldWithPath("data.userId").type(NUMBER).description("작성자 ID"),
                                        fieldWithPath("data.createdAt").type(STRING).description("게시글 작성일자"),
                                        fieldWithPath("data.updatedAt").type(STRING).description("게시글 업데이트일자")
                                ))
                );
    }

    @Test
    @DisplayName("게시글 작성")
    public void create_board() {
        CreateBoardRequest request = new CreateBoardRequest("Test Title", "Test Content", 1L);
        Board board = createBoard();
        given(boardService.create(any(BoardCreateParam.class))).willReturn(board);

        when().body(request)
                .post("/api/v1/boards")
                .then()
                .status(HttpStatus.OK)
                .apply(document("board-create",
                                requestPreprocessor(),
                                responsePreprocessor(),
                                requestFields(
                                        fieldWithPath("title").type(STRING).description("게시글 제목"),
                                        fieldWithPath("content").type(STRING).description("게시글 내용"),
                                        fieldWithPath("userId").type(NUMBER).description("작성자 ID")),
                                responseFields(
                                        fieldWithPath("result").type(STRING).description("성공/실패 결과"),
                                        fieldWithPath("data").type(OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.id").type(NUMBER).description("게시글 ID"),
                                        fieldWithPath("data.title").type(STRING).description("게시글 제목"),
                                        fieldWithPath("data.content").type(STRING).description("게시글 내용"),
                                        fieldWithPath("data.userId").type(NUMBER).description("작성자 ID"),
                                        fieldWithPath("data.createdAt").type(STRING).description("게시글 작성일자"),
                                        fieldWithPath("data.updatedAt").type(STRING).description("업데이트 일자")
                                ))
                );
    }

    // 게시글 생성
    private Board createBoard() {
        return Board.builder()
                .id(1L)
                .title("Test Title")
                .content("Test Content")
                .userId(1L)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}