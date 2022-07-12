package com.smart.project.web.home.act;

import com.smart.project.proc.PlaceRepository;
import com.smart.project.web.home.biz.HomeService;
import com.smart.project.web.home.biz.PhotoService;
import com.smart.project.web.home.biz.PlaceService;
import com.smart.project.web.vo.PlaceVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/managePlace")
public class ManagePlace {
    private final PlaceService placeService;


    @RequestMapping("")
    public String managePlace(Model model){
        List<PlaceVO> allPlace = placeService.selectAllPlaceJoinMainImageName();
        model.addAttribute("allPlace", allPlace);
        return "managePlaces";
    }

    @GetMapping("/{placeNum}")
    public String item(@PathVariable int placeNum, Model model) {

        return "managePlace";
    }


}
