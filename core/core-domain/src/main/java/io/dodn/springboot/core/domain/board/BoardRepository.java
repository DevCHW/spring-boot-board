package io.dodn.springboot.core.domain.board;

import java.util.List;

public interface BoardRepository {

    Board create(BoardCreateParam param);

    Board findById(Long boardId);

    List<Board> findAll();

    Board modify(BoardModifyParam param);
}
