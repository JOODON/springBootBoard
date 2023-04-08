package com.example.springBootBoard.dto;

import com.example.springBootBoard.entity.CommentEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.stream.events.Comment;
import java.time.LocalDateTime;

@Getter @Setter
@ToString
public class CommentDto {

    private Long id;

    private String commentWriter;

    private String commentContents;

    private Long boardId;

    private LocalDateTime commentCreatedTime;

    public static CommentDto toCommentDto(CommentEntity commentEntity,Long boardId) {
        CommentDto commentDto=new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setCommentContents(commentEntity.getCommentContests());
        commentDto.setCommentWriter(commentEntity.getCommentWriter());
        commentDto.setCommentCreatedTime(commentEntity.getCreatedTime());
        commentDto.setBoardId(boardId);

        return commentDto;
    }
}
