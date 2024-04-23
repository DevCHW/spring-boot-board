package io.dodn.springboot.core.api.board.v1.request;

import io.dodn.springboot.core.domain.board.BoardModifyParam;

public record ModifyBoardRequest(
        String title,
        String content
) {
    public BoardModifyParam toParam(Long boardId) {
        return BoardModifyParam.builder()
                .boardId(boardId)
                .title(title)
                .content(content)
                .build();
    }
}
