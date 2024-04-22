package io.dodn.springboot.storage.db.core.board;

import io.dodn.springboot.core.domain.board.Board;
import io.dodn.springboot.core.domain.board.BoardCreateParam;
import io.dodn.springboot.core.domain.board.BoardModifyParam;
import io.dodn.springboot.core.domain.board.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardCoreRepository implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;

    @Override
    public Board create(BoardCreateParam param) {
        BoardEntity boardEntity = BoardEntity.from(param);
        return boardJpaRepository.save(boardEntity).toBoard();
    }

    @Override
    public Board findById(Long boardId) {
        BoardEntity boardEntity = boardJpaRepository.findById(boardId)
                .orElseThrow(EntityNotFoundException::new);
        return boardEntity.toBoard();
    }

    @Override
    public List<Board> findAll() {
        List<BoardEntity> boardEntities = boardJpaRepository.findAll();
        return boardEntities.stream().map(BoardEntity::toBoard)
                .toList();

    }

    @Override
    public Board modify(BoardModifyParam param) {
        BoardEntity boardEntity = boardJpaRepository.findById(param.boardId())
                .orElseThrow(EntityNotFoundException::new);
        boardEntity.modify(param);
        return boardEntity.toBoard();
    }
}
