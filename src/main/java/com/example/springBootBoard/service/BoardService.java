package com.example.springBootBoard.service;
import com.example.springBootBoard.dto.BoardDto;
import com.example.springBootBoard.entity.BoardEntity;
import com.example.springBootBoard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//DTO-> Entity (Entity Class)
//Entity ->DTO (DTO Class)
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;


    public void save(BoardDto boardDto) {
        BoardEntity boardEntity=BoardEntity.toSaveEntity(boardDto);
        boardRepository.save(boardEntity);
    }
    public List<BoardDto> findAll(){
        List<BoardEntity> boardEntityList=boardRepository.findAll();

        List<BoardDto> boardDtoList=new ArrayList<>();
        for(BoardEntity boardEntity : boardEntityList){
            boardDtoList.add(BoardDto.toBoardDto(boardEntity));
        }
        return boardDtoList;
    }
    @Transactional
    //우리가 암시적으로 지정한 쿼리를 실행하는 경우에는 이런식으로 처리해주기!
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDto findById(Long id){
        Optional<BoardEntity> optionalBoardEntity=boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()){
            BoardEntity boardEntity=optionalBoardEntity.get();
            BoardDto boardDto=BoardDto.toBoardDto(boardEntity);
            return boardDto;
        }else {
            return null;
        }
    }

    public BoardDto update(BoardDto boardDto) {
        BoardEntity boardEntity=BoardEntity.toUpdateEntity(boardDto);
        boardRepository.save(boardEntity);

        return findById(boardDto.getId());
        //save 기능으로 inSert랑 Update 두개가 가능함 ID 값이 있으면 UpDate 쿼리가 됨
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
