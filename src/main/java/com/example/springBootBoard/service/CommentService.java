package com.example.springBootBoard.service;


import com.example.springBootBoard.dto.CommentDto;
import com.example.springBootBoard.entity.BoardEntity;
import com.example.springBootBoard.entity.CommentEntity;
import com.example.springBootBoard.repository.BoardRepository;
import com.example.springBootBoard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Long save(CommentDto commentDto) {
        // 부모 (BoardEntity) 조회
        Optional<BoardEntity> optionalBoardEntity=boardRepository.findById(commentDto.getBoardId());
        if (optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();//값을 받아오기
            CommentEntity commentEntity=CommentEntity.toSaveEntity(commentDto,boardEntity);

            return commentRepository.save(commentEntity).getId();//저장하고 아이디값 리턴
        }//조회가 된다면 조회
        else {
            return null;
        }//아니면 Null
    }

    public List<CommentDto> findAll(Long boardId) {
        //댓글 처리 목록 쿼리는 대충
        //select * from comment_table where board_id = ? order by id desc;
        BoardEntity boardEntity=boardRepository.findById(boardId).get();

        List<CommentEntity> commentEntityList=commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);

        //Entity List -> Dto 리스트로 변환만 해주기
        List<CommentDto> commentDtoList=new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList){
            CommentDto commentDto = CommentDto.toCommentDto(commentEntity , boardId);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }
}
