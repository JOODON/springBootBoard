package com.example.springBootBoard.dto;

import com.example.springBootBoard.entity.BoardEntity;
import com.example.springBootBoard.entity.BoardFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private List<MultipartFile> boardFile; // 스프링에서 제공하는 파일을 담아주는 객체
    //멀티파일일 경우애는 리스트로 추가하기!
    //Save.html -> Controller 로넘어올떄 파일을 담는 용도

    private List<String>  originalFileName; //파일 이름

    private List<String> storedFileName; //서버 저장용 파일

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
        if (boardEntity.getFileAttached() == 0){
            boardDto.setFileAttached(boardEntity.getFileAttached()); //0
        }
        else {
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();
            boardDto.setFileAttached(boardEntity.getFileAttached()); //1

            //파일 이름을 가져가야함
            //원본파일 변환파일 : boardFileEntity 가 가지고 있지만 연관관계를 통해 가져올수있음
            //Join Query : SELECT * FROM board _b , board_File_table bf where b.id =bf.board_id and where b.id=?
            //이걸 이런식으로 처리가 가능함!

            for (BoardFileEntity boardFileEntity:boardEntity.getBoardFileEntityList()){
                originalFileNameList.add(boardFileEntity.getOriginalFileName());
                storedFileNameList.add(boardFileEntity.getStoredFileName());
            }

            boardDto.setOriginalFileName(originalFileNameList);
            boardDto.setStoredFileName(storedFileNameList);

        }
        return boardDto;
    }
}
