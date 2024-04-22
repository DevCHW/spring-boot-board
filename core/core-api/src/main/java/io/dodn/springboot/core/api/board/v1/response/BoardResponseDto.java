package io.dodn.springboot.core.api.board.v1.response;

import io.dodn.springboot.core.domain.board.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private Long userId;

    @Builder(access = AccessLevel.PRIVATE)
    public BoardResponseDto(Long id, String title, String content, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public static BoardResponseDto from(Board board) {
        return BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .userId(board.getUserId())
                .build();
    }
}
