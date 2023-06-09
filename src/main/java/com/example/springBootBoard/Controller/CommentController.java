package com.example.springBootBoard.Controller;


import com.example.springBootBoard.dto.CommentDto;
import com.example.springBootBoard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/save")

    public ResponseEntity save(@ModelAttribute CommentDto commentDto){
        System.out.println("commentDto :" + commentDto);
        Long saveResult=commentService.save(commentDto);
        if (saveResult != null){
            //작성 성공 하면 댓글 목록을 가져와서 리턴하주기
            //댓글 목록 : 해당 게시글의 댓글 전체
            List<CommentDto> commentDtoList = commentService.findAll(commentDto.getBoardId());
            return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
            //바디와 Head 둘다 다룰수 있는 객체 첫번쨰는 헤드 두번쨰는 바디부분이 들어감
        }
        else {
            return new ResponseEntity<>("헤당 게시글이 존재하지 않습니다.",HttpStatus.NOT_FOUND);
        }
    }

}
