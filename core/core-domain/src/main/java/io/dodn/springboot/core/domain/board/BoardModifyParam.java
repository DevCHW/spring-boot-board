package io.dodn.springboot.core.domain.board;

import lombok.Builder;

@Builder
public record BoardModifyParam (
        Long boardId,
        String title,
        String content
) {

}
