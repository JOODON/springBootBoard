package com.example.springBootBoard.Controller;

import com.example.springBootBoard.dto.BoardDto;
import com.example.springBootBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor//생성자 방식으로 빈을 주입 받기
@RequestMapping("/board")
//최상위 경로
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }
    @PostMapping("/save")
    public String saveAction(@ModelAttribute BoardDto boardDto){//네임값 DTO로 자동 변경
        boardService.save(boardDto);
        return "index";
    }
    @GetMapping("/")
    public String listForm(Model model){
        return "list";
    }
}
