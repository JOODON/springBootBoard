package com.example.springBootBoard.service;
import com.example.springBootBoard.dto.BoardDto;
import com.example.springBootBoard.entity.BoardEntity;
import com.example.springBootBoard.entity.BoardFileEntity;
import com.example.springBootBoard.repository.BoardFileRepository;
import com.example.springBootBoard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//DTO-> Entity (Entity Class)
//Entity ->DTO (DTO Class)
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    public void save(BoardDto boardDto)throws IOException {
        // 파일 첨부 여부에 따라 로직 분리
        if (boardDto.getBoardFile().isEmpty()){
            BoardEntity boardEntity=BoardEntity.toSaveEntity(boardDto);
            boardRepository.save(boardEntity);
            //첨부 파일이 없는경우 처리해줄 로직
        }
        else {
            MultipartFile boardFile=boardDto.getBoardFile();
            String originalFileName=boardFile.getOriginalFilename();//파일의 이름을 가지고 오는 부분
            String storedFileName = System.currentTimeMillis() + "_" +originalFileName;
            //1970년부터 언제나 지났는지 메소드인데 그냥 파일 이름 만들어주기~
            String savePath= "C:/springboot_images/"+storedFileName;//내가 이 위치에 폴더 만들기! 이곳에 저장될 예정
            boardFile.transferTo(new File(savePath)); //이 부분에서 실제로 저장되는 부분! ->이 부분에서 예외처리!
            BoardEntity boardEntity=BoardEntity.toSaveFileEntity(boardDto);
            Long saveId=boardRepository.save(boardEntity).getId();//부모 자식 관계 [자식 테이블 부모가 어떤 번호인지 필요함] PK값 추출

            BoardEntity board=boardRepository.findById(saveId).get();//[부모 엔티티를 다시 부모로부터 가져옴!]저장한놈의 Id값을 가쟈와야댐

            BoardFileEntity boardFileEntity=BoardFileEntity.toBoardFileEntity(boardEntity,originalFileName,storedFileName);
            //부모엔티티 오리지널파일 그냥 변환 파일순으로 넘겨주기

            boardFileRepository.save(boardFileEntity);
            //DB에 저장까지
        }
    }

    @Transactional
    //여기도 OriginalFileName 을 가져왔기 때문에 이런식으로 처리해주기!
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

    @Transactional
    //부모가 자식을 불렀기 때문에 여기도 이런식으로 처리해주기!
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
        //save 기능으로 insert 랑 Update 두개가 가능함 ID 값이 있으면 UpDate 쿼리가 됨
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
