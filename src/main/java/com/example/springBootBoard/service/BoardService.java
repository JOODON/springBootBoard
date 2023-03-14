package com.example.springBootBoard.service;
import com.example.springBootBoard.dto.BoardDto;
import com.example.springBootBoard.entity.BoardEntity;
import com.example.springBootBoard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
