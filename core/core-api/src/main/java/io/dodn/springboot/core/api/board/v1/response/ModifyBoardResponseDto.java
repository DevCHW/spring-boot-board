package io.dodn.springboot.core.api.board.v1.response;

import io.dodn.springboot.core.domain.board.Board;
import lombok.AccessLevel;
import lombok.Builder;

public class ModifyBoardResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final Long userId;

    @Builder(access = AccessLevel.PRIVATE)
    public ModifyBoardResponseDto(Long id, String title, String content, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public static ModifyBoardResponseDto from(Board board) {
        return ModifyBoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .userId(board.getUserId())
                .build();
    }

}
