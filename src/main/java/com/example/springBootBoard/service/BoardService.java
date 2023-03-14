package com.example.springBootBoard.service;
import com.example.springBootBoard.dto.BoardDto;
import com.example.springBootBoard.entity.BoardEntity;
import com.example.springBootBoard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
