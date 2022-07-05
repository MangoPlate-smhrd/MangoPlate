package com.smart.project.web.home.act;

import com.smart.project.web.home.biz.HomeService;
import com.smart.project.web.home.biz.PhotoService;
import com.smart.project.web.vo.ListVO;
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
@RequestMapping("/listsManage")
public class ListManage {
    private final HomeService homeService;
    private final PhotoService photoService;
    @RequestMapping("")
    public String listsManageHome(Model model){
        List<ListVO> allList = homeService.selectAllList();
        model.addAttribute("allList", allList);
        return "listsManage";
    }


    @RequestMapping("/listsInsert")
    public String listsManagerInsertPage(Model model){
        model.addAttribute("lists", new ListVO());
        return "listsInsert";
    }

    @RequestMapping(value="/listsInsert", params="listName")
    public String listsManagerInsert(Model model, @ModelAttribute ListVO listVO, @RequestParam("file") MultipartFile file){
        log.error("lists = {}", listVO);
        String path = photoService.savePhoto(file, "list_img");
        listVO.setMainImageName(path);

        homeService.insertList(listVO);
        return "redirect:/listsManage";
    }
}
