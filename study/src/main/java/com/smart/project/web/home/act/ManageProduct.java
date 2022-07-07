package com.smart.project.web.home.act;

import com.smart.project.web.home.biz.HomeService;
import com.smart.project.web.home.biz.PhotoService;
import com.smart.project.web.vo.ListVO;
import com.smart.project.web.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/manageProduct")
public class ManageProduct {
    private final HomeService homeService;
    private final PhotoService photoService;
    @RequestMapping("")
    public String manageListHome(Model model){
        return "manageList";
    }
}
