package io.dodn.springboot.core.domain.board;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Board {

    private final Long id;
    private final String title;
    private final String content;
    private final Long userId;

    @Builder
    public Board(Long id, String title, String content, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

}
