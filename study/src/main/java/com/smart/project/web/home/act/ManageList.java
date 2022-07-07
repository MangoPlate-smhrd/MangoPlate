package com.smart.project.web.home.act;

import com.smart.project.common.vo.InternCookie;
import com.smart.project.web.home.biz.HomeService;
import com.smart.project.web.home.biz.PhotoService;
import com.smart.project.web.vo.ListVO;
import com.smart.project.web.vo.MainImageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/manageList")
public class ManageList {
    private final HomeService homeService;
    private final PhotoService photoService;
    @RequestMapping("")
    public String manageListHome(Model model){
        List<ListVO> allList = homeService.selectAllList();
        model.addAttribute("allList", allList);
        return "manageList";
    }


    @RequestMapping("/insertList")
    public String manageListInsertPage(Model model){
        model.addAttribute("lists", new ListVO());
        return "insertList";
    }

    @RequestMapping(value="/insertList", params="listName")
    public String manageListInsert(Model model, @ModelAttribute ListVO listVO, @RequestParam("file") MultipartFile file, InternCookie internCookie){
        log.error("lists = {}", listVO);

        String fileName = photoService.savePhoto(file, internCookie);
        listVO.setMainImageName(fileName);

        homeService.insertList(listVO);
        homeService.insertMainImage(listVO);
        return "redirect:/manageList";
    }

    @RequestMapping("/deleteList")
    public String manageListDelete(@RequestParam int listNum){
        if(homeService.selectAllCategoryProduct(listNum).isEmpty()){
            homeService.deleteMainImage(listNum);
            homeService.deleteList(listNum);
        }else{
            return "redirect:/manageList?deleteResult=false";
        }

        return "redirect:/manageList";
    }

    @RequestMapping("/updateList")
    public String manageUpdataListHome(@RequestParam int listNum, Model model){
        model.addAttribute("lists", homeService.selectList(listNum));
        return "updateList";
    }

    @RequestMapping(value="/updateList", params="listName")
    public String manageUpdateList(@ModelAttribute ListVO listVO){
        homeService.updateList(listVO);
        return "redirect:/manageList";
    }

    @RequestMapping("/updateMainImage")
    public String manageUpdateMainImageHome(Model model, @RequestParam int listNum){

        model.addAttribute("mainImageVO", new MainImageVO(listNum));
        return "updateMainImage";
    }

    @RequestMapping(value = "/updateMainImage", params = "mainImageNum")
    public String manageUpdateMainImage(@RequestParam("file") MultipartFile file, @RequestParam int listNum, @ModelAttribute MainImageVO mainImageVO, InternCookie internCookie){

        String fileName = photoService.savePhoto(file, internCookie);
        mainImageVO.setMainImageName(fileName);
        mainImageVO.setListNum(listNum);

        homeService.updateMainImage(mainImageVO);

        return "redirect:/manageList";
    }


}
