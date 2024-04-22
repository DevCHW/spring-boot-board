package io.dodn.springboot.core.domain.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardReader {

    private final BoardRepository boardRepository;

    public List<Board> readBoards() {
        return boardRepository.findAll();
    }

    public Board read(Long boardId) {
        return boardRepository.findById(boardId);
    }
}
