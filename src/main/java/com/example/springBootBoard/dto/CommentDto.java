package com.example.springBootBoard.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
public class CommentDto {

    private Long id;

    private String commentWriter;

    private String commentContents;

    private Long boardId;

    private LocalDateTime commentCreatedTime;

}
