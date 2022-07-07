package com.smart.project.web.home.act;

import com.smart.project.util.ClientUtil;
import com.smart.project.web.home.biz.HomeService;
import com.smart.project.web.vo.ListVO;
import com.smart.project.web.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeAct {
    private final HomeService homeService;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) throws Exception {
        List<ListVO> lists = homeService.selectAllList();
        model.addAttribute("lists", lists);
        log.error("homePage");

        Map<String, String> cookieMap = ClientUtil.getCurrentCookie(request);
        String id = cookieMap.get("id");
        log.error("main id ==> {}", id);

        model.addAttribute("login", id);

        return "index";
    }

    @RequestMapping("/lists/{param}")
    public String lists(@PathVariable int param, Model model, HttpServletRequest request){
        List<ProductVO> product = homeService.selectAllCategoryProduct(param);
        model.addAttribute("places", product);
        log.error("lists act");
        log.error("lists url : {}", request.getRequestURI());
        log.error("places : {}", product);
        return "lists";
    }



    @RequestMapping("/product/{param}")
    public String product(@PathVariable int param,Model model, HttpServletRequest request){
        ProductVO product = homeService.selectProduct(param);
        model.addAttribute("place", product);
        log.error("lists act");
        log.error("lists url : {}", request.getRequestURI());
        log.error("place : {}", product);
        return "product";
    }

    @RequestMapping("/product/delete")
    public String delete(){
        return "redirect:/lists/83";
    }

}
