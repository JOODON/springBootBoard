package com.example.springBootBoard.Controller;

import com.example.springBootBoard.dto.BoardDto;
import com.example.springBootBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String findById(@PathVariable Long id,Model model,@PageableDefault(page = 1) Pageable pageable){
        //해당 게시글의 조회수를 하나 올리고 detail.html에 출력!
        boardService.updateHits(id);
        //조회수 증가 메소드
        BoardDto boardDto=boardService.findById(id);

        model.addAttribute("board",boardDto);
        model.addAttribute("page",pageable.getPageNumber());

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
    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable Long id){
        boardService.delete(id);

        return "redirect:/board/";
    }
    // /board/paging?page=1;
    @GetMapping("/paging")
    public String paging(@PageableDefault (page = 1) Pageable pageable,Model model){
        //pageable.getPageNumber();몇 페이지가 요청 되었는지 확인!
        Page<BoardDto> boardList=boardService.paging(pageable);
        //page 개수가 20개 // 3페이지 1 2 3  7페이지 이면 7 8 9
        int blockLimit = 3;//페이지 갯수의 변수값
        int startPage=(((int)Math.ceil((double) pageable.getPageNumber() / blockLimit))-1) * blockLimit + 1;
        //현재 사용자가 요청한 페이지를 3으로 나눠서 -1 로 빼기 * 3 + 1 => 1,4,7,10
        int endPage=((startPage + blockLimit -1) < boardList.getTotalElements()) ?
                startPage + blockLimit - 1 :
                boardList.getTotalPages() ;

        model.addAttribute("boardList",boardList);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        return "paging";
    }
}
