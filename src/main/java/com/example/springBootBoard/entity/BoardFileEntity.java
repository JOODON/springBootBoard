package com.example.springBootBoard.entity;

import com.example.springBootBoard.dto.BoardDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "bpard_file_table")
public class BoardFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    //Board Entity 와 BoardFileEntity 관계를 맺음
    //게시글과 파일 기준에서는 N -> 1이 댐
    //FetchType FetchType.LAZY 부모 자식 관계에서 부모랑 자식 관계에서 필요할때만 가져오기
    //JoinColum 은 테이블에 만들어지는 컬럼 이름을 말해줌! 특정 부모 Entity 가져오기~

    public static BoardFileEntity toBoardFileEntity(BoardEntity boardEntity,String originalFileName,String storedFileName){
        BoardFileEntity boardFileEntity=new BoardFileEntity();
        boardFileEntity.setOriginalFileName(originalFileName);
        boardFileEntity.setOriginalFileName(storedFileName);
        boardFileEntity.setBoardEntity(boardEntity);

        return boardFileEntity;
    }
}
