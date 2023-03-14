package com.example.springBootBoard.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@NoArgsConstructor//기본 생성자 생성하겠다고 선언
@AllArgsConstructor//모든 필두를 참고하는 생성자 선언
public class BoardDto {
    private Long id;

    private String boardWriter;

    private String boardPass;

    private String boardTitle;

    private String boardContents;

    private int boardHits;//조회수

    private LocalDateTime localDateTime;//글 작성시간

    private LocalDateTime boardUpdateTime;//글 수정시간

}
