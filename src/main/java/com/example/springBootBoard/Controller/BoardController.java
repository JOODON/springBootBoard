package com.example.springBootBoard.Controller;

import com.example.springBootBoard.dto.BoardDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
//최상위 경로
public class BoardController {

    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }
    @PostMapping("/save")
    public String saveAction(@ModelAttribute BoardDto boardDto){//네임값 DTO로 자동 변경
        return "save";
    }
    @GetMapping("/")
    public String listForm(Model model){
        return "list";
    }
}
