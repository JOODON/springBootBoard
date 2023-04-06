package com.example.springBootBoard.Controller;


import com.example.springBootBoard.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    @PostMapping("/save")
    public @ResponseBody String save(@ModelAttribute CommentDto commentDto){
        System.out.println("commentDto :" + commentDto);
        return "요청 성공";
    }
}
