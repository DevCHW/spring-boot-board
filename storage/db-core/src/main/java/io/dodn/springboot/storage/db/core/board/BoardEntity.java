package io.dodn.springboot.storage.db.core.board;

import io.dodn.springboot.core.domain.board.Board;
import io.dodn.springboot.core.domain.board.BoardCreateParam;
import io.dodn.springboot.core.domain.board.BoardModifyParam;
import io.dodn.springboot.storage.db.core.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "board")
public class BoardEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    private Long userId;

    @Builder(access = AccessLevel.PRIVATE)
    public BoardEntity(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public static BoardEntity from(BoardCreateParam param) {
        return BoardEntity.builder()
                .title(param.title())
                .content(param.content())
                .userId(param.userId())
                .build();
    }

    public Board toBoard() {
        return Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .userId(userId)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    public void modify(BoardModifyParam param) {
        this.title = param.title();
        this.content = param.content();
    }

}
