package com.example.springBootBoard.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AjaxViewController {
    @GetMapping("/ajax")
    public String ajaxTest(){
        return "Ajax/ajaxindex";
    }

    @GetMapping("/ajax-ex-01")
    public String ajaxEx01(){
        return "Ajax/ajax-ex-01";
    }

    @GetMapping("/ajax-ex-02")
    public String ajaxEx02(){
        return "Ajax/ajax-ex-02";
    }

    @GetMapping("/ajax-ex-03")
    public String ajaxEx03(){
        return "Ajax/ajax-ex-03";
    }

    @GetMapping("/ajax-ex-04")
    public String ajaxEx04(){
        return "Ajax/ajax-ex-04";
    }

    @GetMapping("/ajax-ex-05")
    public String ajaxEx05(){
        return "Ajax/ajax-ex-05";
    }

    @GetMapping("/ajax-ex-06")
    public String ajaxEx06(){
        return "Ajax/ajax-ex-06";
    }

    @GetMapping("/ajax-ex-07")
    public String ajaxEx07(){
        return "Ajax/ajax-ex-07";
    }

    @GetMapping("/ajax-ex-08")
    public String ajaxEx08(){
        return "Ajax/ajax-ex-08";
    }
    @GetMapping("/ajax-ex-09")
    public String ajaxEx09(){
        return "Ajax/ajax-ex-09";
    }
    @GetMapping("/ajax-ex-10")
    public String ajaxEx10(){
        return "Ajax/ajax-ex-10";
    }

}
