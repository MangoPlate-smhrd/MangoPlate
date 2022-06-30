package com.smart.project.web.home.act;

import com.smart.project.web.home.biz.HomeService;
import com.smart.project.web.vo.ListVO;
import com.smart.project.web.vo.PlaceVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("lists", lists);
        log.error("homePage");
        return "index";
    }

    @RequestMapping("/lists/{param}")
    public String lists(@PathVariable int param, Model model, HttpServletRequest request){
        List<PlaceVO> places = homeService.selectAllPlace(param);
        model.addAttribute("places", places);
        log.error("lists act");
        log.error("lists url : {}", request.getRequestURI());
        log.error("places : {}", places);
        return "lists";
    }

    @RequestMapping("/product/{param}")
    public String product(@PathVariable int param,Model model, HttpServletRequest request){
        PlaceVO place = homeService.selectPlace(param);
        model.addAttribute("place", place);
        log.error("lists act");
        log.error("lists url : {}", request.getRequestURI());
        log.error("place : {}", place);
        return "product";
    }

}
