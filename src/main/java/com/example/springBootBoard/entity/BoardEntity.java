package com.example.springBootBoard.entity;

import com.example.springBootBoard.dto.BoardDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column
    private int fileAttached; //1 or 0

    @OneToMany(mappedBy = "boardEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntityList=new ArrayList<>();

    //mappedBy ->무엇과 매칭되는지 boardFileEntity 에 있는 board_Id의 이름을 가진 BoardEntity 랑 엮이기 때문에! 저렇게 지음
    //cascade = CascadeType.REMOVE,orphanRemoval = true 지워지면 같이 지워질수 있게끔 해주는 부분!

    public static BoardEntity toSaveEntity(BoardDto boardDto){
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.setBoardWriter(boardDto.getBoardWriter());
        boardEntity.setBoardPass(boardDto.getBoardPass());
        boardEntity.setBoardTitle(boardDto.getBoardTitle());
        boardEntity.setBoardContents(boardDto.getBoardContents());
        boardEntity.setBoardHits(0);//일단 조회수 값은 0으로 설정
        boardEntity.setFileAttached(0);//파일을 안가져오기 때문에 FileAttached 를0으로 해줌
        return boardEntity;
    }
    public static BoardEntity toSaveFileEntity(BoardDto boardDto){
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.setBoardWriter(boardDto.getBoardWriter());
        boardEntity.setBoardPass(boardDto.getBoardPass());
        boardEntity.setBoardTitle(boardDto.getBoardTitle());
        boardEntity.setBoardContents(boardDto.getBoardContents());
        boardEntity.setBoardHits(0);//일단 조회수 값은 0으로 설정
        boardEntity.setFileAttached(1);//파일을 가져오기 떄문에 1로 설정해주는 모습~
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
