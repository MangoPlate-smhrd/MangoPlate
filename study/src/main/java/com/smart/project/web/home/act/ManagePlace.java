package com.smart.project.web.home.act;

import com.smart.project.proc.Place;
import com.smart.project.web.home.biz.HomeService;
import com.smart.project.web.home.biz.PhotoService;
import com.smart.project.web.vo.PlaceVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/managePlace")
public class ManagePlace {
    private final HomeService homeService;
    private final PhotoService photoService;
    private final Place place;


    @RequestMapping("")
    public String managePlace(Model model){
        List<PlaceVO> allList = place.selectAllPlace();
        model.addAttribute("allList", allList);
        return "managePlace";
    }


}
