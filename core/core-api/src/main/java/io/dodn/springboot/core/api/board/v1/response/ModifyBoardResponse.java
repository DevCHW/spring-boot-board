package io.dodn.springboot.core.api.board.v1.response;

import io.dodn.springboot.core.domain.board.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ModifyBoardResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final Long userId;

    @Builder(access = AccessLevel.PRIVATE)
    public ModifyBoardResponse(Long id, String title, String content, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public static ModifyBoardResponse from(Board board) {
        return ModifyBoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .userId(board.getUserId())
                .build();
    }

}
