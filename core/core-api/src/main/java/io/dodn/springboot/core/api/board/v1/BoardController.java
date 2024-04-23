package io.dodn.springboot.core.api.board.v1;

import io.dodn.springboot.core.api.board.v1.request.CreateBoardRequest;
import io.dodn.springboot.core.api.board.v1.request.ModifyBoardRequest;
import io.dodn.springboot.core.api.board.v1.response.BoardResponse;
import io.dodn.springboot.core.api.board.v1.response.CreateBoardResponse;
import io.dodn.springboot.core.api.board.v1.response.ModifyBoardResponse;
import io.dodn.springboot.core.api.support.response.ApiResponse;
import io.dodn.springboot.core.domain.board.Board;
import io.dodn.springboot.core.domain.board.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/api/v1/boards")
    public ApiResponse<CreateBoardResponse> createBoard(
            @RequestBody @Valid CreateBoardRequest requestDto
    ) {
        Board board = boardService.create(requestDto.toParam());
        return ApiResponse.success(CreateBoardResponse.from(board));
    }

    @GetMapping("/api/v1/boards")
    public ApiResponse<List<BoardResponse>> getBoards() {
        List<Board> boards = boardService.getBoards();
        List<BoardResponse> response = boards.stream().map(BoardResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @GetMapping("/api/v1/boards/{boardId}")
    public ApiResponse<BoardResponse> getBoard(@PathVariable("boardId") Long boardId) {
        Board board = boardService.getBoard(boardId);
        return ApiResponse.success(BoardResponse.from(board));
    }

    @PutMapping("/api/v1/boards/{boardId}")
    public ApiResponse<ModifyBoardResponse> modifyBoard(
            @PathVariable("boardId") Long boardId,
            @RequestBody @Valid ModifyBoardRequest requestDto) {
        Board board = boardService.modifyBoard(requestDto.toParam(boardId));
        return ApiResponse.success(ModifyBoardResponse.from(board));
    }

}
