package io.dodn.springboot.core.api.board.v1.request;

import io.dodn.springboot.core.domain.board.BoardCreateParam;

public record CreateBoardRequest(
        String title,
        String content,
        Long userId
) {
    public BoardCreateParam toParam() {
        return new BoardCreateParam(title, content, userId);
    }
}
