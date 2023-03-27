package com.example.springBootBoard.dto;

import com.example.springBootBoard.entity.BoardEntity;
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

    private LocalDateTime boardCreateTime;//글 작성시간

    private LocalDateTime boardUpdateTime;//글 수정시간

    public BoardDto(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreateTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreateTime = boardCreateTime;
    }

    public static BoardDto toBoardDto(BoardEntity boardEntity){
        BoardDto boardDto=new BoardDto();
        boardDto.setId(boardEntity.getId());
        boardDto.setBoardWriter(boardEntity.getBoardWriter());
        boardDto.setBoardPass(boardEntity.getBoardPass());
        boardDto.setBoardTitle(boardEntity.getBoardTitle());
        boardDto.setBoardContents(boardEntity.getBoardContents());
        boardDto.setBoardHits(boardEntity.getBoardHits());
        boardDto.setBoardCreateTime(boardEntity.getCreatedTime());
        boardDto.setBoardUpdateTime(boardEntity.getUpdatedTime());

        return boardDto;
    }

}
