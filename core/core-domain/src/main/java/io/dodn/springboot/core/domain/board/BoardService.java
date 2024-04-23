package io.dodn.springboot.core.domain.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardReader boardReader;
    private final BoardWriter boardWriter;

    // 게시글 저장
    public Board create(BoardCreateParam param) {
        return boardWriter.create(param);
    }

    // 게시글 목록 조회
    public List<Board> getBoards() {
        return boardReader.readBoards();
    }

    // 게시글 단건 조회
    public Board getBoard(Long boardId) {
        return boardReader.read(boardId);
    }

    // 게시글 수정
    public Board modifyBoard(BoardModifyParam param) {
        return boardWriter.modify(param);
    }

}
