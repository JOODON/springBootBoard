package com.example.springBootBoard.dto;

import com.example.springBootBoard.entity.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile boardFile; // 스프링에서 제공하는 파일을 담아주는 객체
    //Save.html -> Controller 로넘어올떄 파일을 담는 용도

    private String originalFileName; //파일 이름

    private String storedFileName; //서버 저장용 파일

    private int fileAttached; //파일 첨부 여부 (첨부 1,미첨부 0)
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
