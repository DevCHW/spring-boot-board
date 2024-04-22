package io.dodn.springboot.core.domain.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardWriter {

    private final BoardRepository boardRepository;

    public Board create(BoardCreateParam param) {
        return boardRepository.create(param);
    }

    public Board modify(BoardModifyParam param) {
        return boardRepository.modify(param);
    }
}
