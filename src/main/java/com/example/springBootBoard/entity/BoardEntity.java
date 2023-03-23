package com.example.springBootBoard.entity;

import com.example.springBootBoard.dto.BoardDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//DB의 테이블 역활을 하는 클래스
@Entity
@Getter @Setter
@Table(name = "board_table")//이 이름으로 자동으로 테이블을 생성!
public class BoardEntity extends BaseEntity{
    @Id//필수 컬럼 아이디값으로 무엇을 쓸것인가 MY SQL 기준 자동 증가 가능
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20 , nullable = false)//길이는 2자 이상 Null 일수 없다라고 표기해주기!
    private String boardWriter;

    @Column//크기 255 Null가능
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    public static BoardEntity toSaveEntity(BoardDto boardDto){
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.setBoardWriter(boardDto.getBoardWriter());
        boardEntity.setBoardPass(boardDto.getBoardPass());
        boardEntity.setBoardTitle(boardDto.getBoardTitle());
        boardEntity.setBoardContents(boardDto.getBoardContents());
        boardEntity.setBoardHits(0);//일단 조회수 값은 0으로 설정
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDto boardDto) {
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.setId(boardDto.getId());
        boardEntity.setBoardWriter(boardDto.getBoardWriter());
        boardEntity.setBoardPass(boardDto.getBoardPass());
        boardEntity.setBoardTitle(boardDto.getBoardTitle());
        boardEntity.setBoardContents(boardDto.getBoardContents());
        boardEntity.setBoardHits(boardDto.getBoardHits());//일단 조회수 값은 0으로 설정
        return boardEntity;
    }

}
