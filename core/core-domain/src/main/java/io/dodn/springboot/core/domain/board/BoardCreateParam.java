package io.dodn.springboot.core.domain.board;

public record BoardCreateParam(
        String title,
        String content,
        Long userId
) {
}
