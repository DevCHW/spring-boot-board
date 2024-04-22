package io.dodn.springboot.core.api.board.v1;

import io.dodn.springboot.core.api.board.v1.request.CreateBoardRequestDto;
import io.dodn.springboot.core.api.board.v1.request.ModifyBoardRequestDto;
import io.dodn.springboot.core.api.board.v1.response.BoardResponseDto;
import io.dodn.springboot.core.api.board.v1.response.CreateBoardResponseDto;
import io.dodn.springboot.core.api.board.v1.response.ModifyBoardResponseDto;
import io.dodn.springboot.core.api.support.response.ApiResponse;
import io.dodn.springboot.core.domain.board.Board;
import io.dodn.springboot.core.domain.board.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/api/v1/boards")
    public ApiResponse<CreateBoardResponseDto> createBoard(
            @RequestBody @Valid CreateBoardRequestDto requestDto
    ) {
        Board board = boardService.save(requestDto.toParam());
        return ApiResponse.success(CreateBoardResponseDto.from(board));
    }

    @GetMapping("/api/v1/boards")
    public ApiResponse<List<BoardResponseDto>> getBoards() {
        List<Board> boards = boardService.getBoards();
        List<BoardResponseDto> response = boards.stream().map(BoardResponseDto::from)
                .toList();
        return ApiResponse.success(response);
    }

    @GetMapping("/api/v1/boards/{boardId}")
    public ApiResponse<BoardResponseDto> getBoard(@PathVariable("boardId") Long boardId) {
        Board board = boardService.getBoard(boardId);
        return ApiResponse.success(BoardResponseDto.from(board));
    }

    @PutMapping("/api/v1/boards/{boardId}")
    public ApiResponse<ModifyBoardResponseDto> modifyBoard(
            @PathVariable("boardId") Long boardId,
            @RequestBody @Valid ModifyBoardRequestDto requestDto) {
        Board board = boardService.modifyBoard(requestDto.toParam(boardId));
        return ApiResponse.success(ModifyBoardResponseDto.from(board));
    }

    @GetMapping("/api/v2/products/test/product/{saleProductId}")
    public ApiResponse<UUID> getProduct(@PathVariable(value = "saleProductId") UUID saleProductId) {
        System.out.println("saleProductId = " + saleProductId);
        return ApiResponse.success(saleProductId);
    }

}
