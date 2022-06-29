package com.smart.project.web.home.act;

import com.smart.project.web.home.biz.HomeService;
import com.smart.project.web.vo.ListVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeAct {
    private final HomeService homeService;

    @RequestMapping("/")
    public String index(Model model) {
        List<ListVO> lists = homeService.selectList();
        model.addAttribute("list", lists);
        log.error("homePage");
        return "index";
    }

    @RequestMapping("/lists/**")
    public String lists(@RequestParam String param, HttpServletRequest request){
        log.error("lists act");
        log.error("lists url : {}", request.getRequestURI());
        log.error("param : {}", param);
        return "lists";
    }

    @RequestMapping("/product/**")
    public String product(HttpServletRequest request){
        log.error("lists act");
        log.error("lists url : {}", request.getRequestURI());
        return "product";
    }

}
