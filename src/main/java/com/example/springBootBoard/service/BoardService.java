package com.example.springBootBoard.service;
import com.example.springBootBoard.dto.BoardDto;
import com.example.springBootBoard.entity.BoardEntity;
import com.example.springBootBoard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<BoardDto> paging(Pageable pageable) {
        int page=pageable.getPageNumber() - 1;
        int pageLimit = 3;
        Page<BoardEntity> boardEntities =
                boardRepository.findAll(PageRequest.of(page,pageLimit,Sort.by(Sort.Direction.DESC,"id")));

        System.out.println("boardEntities.getContent()"+boardEntities.getContent());//요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements()"+boardEntities.getTotalElements());//전체 글 갯수
        System.out.println("boardEntities.getNumber()"+boardEntities.getNumber());//DB로 요청한 페이지 번호
        System.out.println("boardEntities.getSize()"+boardEntities.getSize());//한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious()"+boardEntities.hasPrevious());//이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst()"+boardEntities.isFirst());//첫 페이지 여부
        System.out.println("boardEntities.isLast()"+boardEntities.isLast());//마지막 페이지 여부

        //목록 :id writer ,title ,hits ,createTime
        Page<BoardDto> boardDTOS=boardEntities.map
                (board -> new BoardDto(board.getId(),board.getBoardWriter(),board.getBoardTitle(),
                        board.getBoardHits(),board.getCreatedTime()));

        return boardDTOS;
    }
}
