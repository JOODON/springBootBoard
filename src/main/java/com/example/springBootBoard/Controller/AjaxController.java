package com.example.springBootBoard.Controller;

import com.example.springBootBoard.dto.AjaxDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AjaxController {
    @GetMapping("/ex01")
    public String ex01(){
        System.out.println("AjaxController.ex 01");
        return "Ajax/ajax-ex-01"; //Ajax/ajax-ex-01 -> 에있는 값 출력
    }

    @PostMapping("/ex02")
    public @ResponseBody String ex02(){
        //Post 로 받을 경우에는 위와 같은 ResponseBody 를 붙혀주기
        System.out.println("AjaxController.ex 02");
        return "Ajax/ajax-ex-02"; //return 값을 받아서 그냥 보내줌 ㅎㅎ
    }

    @GetMapping ("/ex03")
    public @ResponseBody String ex03(@RequestParam("param1") String param1,
                                     @RequestParam("param2") String param2){
        //Post 로 받을 경우에는 위와 같은 ResponseBody 를 붙혀주기
        System.out.println(param1 + " " + param2 + " " );
        return "Ajax/ajax-ex-03";
    }

    @PostMapping ("/ex04")
    public @ResponseBody String ex04(@RequestParam("param1") String param1,
                                     @RequestParam("param2") String param2){
        //Post 로 받을 경우에는 위와 같은 ResponseBody 를 붙혀주기
        System.out.println(param1 + " " + param2 + " " );
        return "Ajax/ajax-ex-04";
    }

    @GetMapping ("/ex05")
    public @ResponseBody AjaxDto ex05(@ModelAttribute AjaxDto ajaxDto){
        //객체로 받을떄는 위와 같이 해서 받아주기 생략은 가능하지만 일단은 쓰는걸로 하자
        System.out.println("ajaxDto = " + ajaxDto);

        return ajaxDto;
    }

    @PostMapping ("/ex06")
    public @ResponseBody AjaxDto ex06(@ModelAttribute AjaxDto ajaxDto){
        //객체로 받을떄는 위와 같이 해서 받아주기 생략은 가능하지만 일단은 쓰는걸로 하자
        System.out.println("ajaxDto = " + ajaxDto);

        return ajaxDto;
    }

    @PostMapping ("/ex07")
    public @ResponseBody AjaxDto ex07(@RequestBody AjaxDto ajaxDto){

        System.out.println("ajaxDto = " + ajaxDto);
        return ajaxDto;
    }

    private List<AjaxDto> dtoList(){
        List<AjaxDto> dtoList = new ArrayList<>();
        dtoList.add(new AjaxDto("data1","data2"));
        dtoList.add(new AjaxDto("data1","data2"));
        return dtoList;
    }

    @PostMapping ("/ex08")
    public @ResponseBody List<AjaxDto> ex08(@RequestBody AjaxDto ajaxDto){
        List<AjaxDto> dtoList=dtoList();
        dtoList.add(ajaxDto);
        System.out.println("ajaxDto = " + ajaxDto);
        return dtoList;
    }

    @PostMapping ("/ex09")
    public ResponseEntity ex09(@RequestBody AjaxDto ajaxDto){
        System.out.println("ajaxDto = " + ajaxDto);
        return new ResponseEntity(ajaxDto , HttpStatus.OK);
        //new ResponseEntity 를 생성하여 보내줌
        //데이터와 응답 코드를 같이 보내줄수 있음!
    }

    @PostMapping ("/ex10")
    public ResponseEntity ex10(@RequestBody AjaxDto ajaxDto){
        System.out.println("ajaxDto = " + ajaxDto);
        List<AjaxDto> dtoList=dtoList();
        dtoList.add(ajaxDto);
        return new ResponseEntity(dtoList , HttpStatus.OK);
    }
}
