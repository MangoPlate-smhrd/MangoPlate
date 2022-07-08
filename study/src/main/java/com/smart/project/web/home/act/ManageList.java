package com.smart.project.web.home.act;

import com.smart.project.common.vo.InternCookie;
import com.smart.project.web.home.biz.HomeService;
import com.smart.project.web.home.biz.PhotoService;
import com.smart.project.web.home.validator.ManageListValidator;
import com.smart.project.web.vo.ListVO;
import com.smart.project.web.vo.MainImageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/manageList")
public class ManageList {
    private final HomeService homeService;
    private final PhotoService photoService;
    private final ManageListValidator manageListValidator;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(manageListValidator);
    }
    @GetMapping("")
    public String manageListHome(Model model){
        List<ListVO> allList = homeService.selectAllList();
        model.addAttribute("allList", allList);
        return "manageList";
    }


    /**
     * list 삽입
     * */
    @GetMapping("/insertList")
    public String manageListInsertPage(Model model){
        model.addAttribute("listVO", new ListVO());
        return "insertList";
    }

    @PostMapping(value="/insertList")
    public String manageListInsert(Model model, @Validated @ModelAttribute ListVO listVO, BindingResult bindingResult, @RequestParam("file") MultipartFile file, InternCookie internCookie){

        //Map<String, String> errors = new HashMap<>();

        if(file.isEmpty()){
            //errors.put("listMainImage", "대표이미지는 필수입니다");
            //bindingResult.addError(new ObjectError("listVO", "대표이미지는 필수입니다."));
            bindingResult.reject("file");
        }

        if(bindingResult.hasErrors()){
            log.error("errors : {}", bindingResult);
            return "insertList";
        }

        String fileName = photoService.savePhoto(file, internCookie);
        listVO.setMainImageName(fileName);

        homeService.insertList(listVO);
        homeService.insertMainImage(listVO);
        return "redirect:/manageList";
    }


    /**
     *list 업데이트
     *  */
    @GetMapping("/{listNum}/updateList")
    public String manageUpdateListHome(@PathVariable int listNum, Model model){
        model.addAttribute("lists", homeService.selectList(listNum));
        return "updateList";
    }

    @PostMapping(value="/updateList", params="listName")
    public String manageUpdateList(@ModelAttribute ListVO listVO){
        homeService.updateList(listVO);
        return "redirect:/manageList";
    }

    @GetMapping("/{listNum}/updateMainImage")
    public String manageUpdateMainImageHome(@PathVariable int listNum, Model model){
        model.addAttribute("mainImageVO", new MainImageVO(listNum));
        return "updateMainImage";
    }

    @PostMapping(value = "/updateMainImage", params = "mainImageNum")
    public String manageUpdateMainImage(@RequestParam("file") MultipartFile file, @RequestParam int listNum, @ModelAttribute MainImageVO mainImageVO, InternCookie internCookie){

        log.error("mainImageNum : {}", mainImageVO);
        String selectMainImageNameListNum = homeService.selectMainImageNameListNum(listNum);
        String fileName = photoService.updatePhoto(file, internCookie, selectMainImageNameListNum);
        mainImageVO.setMainImageName(fileName);
        mainImageVO.setListNum(listNum);

        homeService.updateMainImage(mainImageVO);

        return "redirect:/manageList";
    }


    /**
     * list 삭제
     * */
    @PostMapping("/deleteList")
    public String manageListDelete(@RequestParam int listNum){
        if(homeService.selectAllCategoryProduct(listNum).isEmpty()){
            homeService.deleteMainImage(listNum);
            homeService.deleteList(listNum);
        }else{
            return "redirect:/manageList?deleteResult=false";
        }

        return "redirect:/manageList";
    }




}
