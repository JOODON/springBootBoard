package com.example.springBootBoard.Controller;

import com.example.springBootBoard.dto.BoardDto;
import com.example.springBootBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        //DB에서 전체 게시글 데이터를 가져와서 list.html에다가 불러주기
        List<BoardDto> boardDtoList = boardService.findAll();
        model.addAttribute("boardList",boardDtoList);
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id,Model model){
        //해당 게시글의 조회수를 하나 올리고 detail.html에 출력!
        boardService.updateHits(id);

        //조회수 증가 메소드
        BoardDto boardDto=boardService.findById(id);

        model.addAttribute("board",boardDto);
        return "detail";
    }
    @GetMapping("/update/{id}")
    public String upDateForm(@PathVariable Long id,Model model) {
        BoardDto boardDto=boardService.findById(id);
        model.addAttribute("boardUpdate",boardDto);
        return "update";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDto boardDto,Model model){
        BoardDto board=boardService.update(boardDto);
        model.addAttribute("board",board);

        return "detail";
    }
}
