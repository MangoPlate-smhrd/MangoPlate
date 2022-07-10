package com.smart.project.web.home.act;

import com.smart.project.common.vo.InternCookie;
import com.smart.project.proc.Place;
import com.smart.project.util.ClientUtil;
import com.smart.project.web.home.biz.HomeService;
import com.smart.project.web.home.biz.PhotoService;
import com.smart.project.web.home.biz.PlaceService;
import com.smart.project.web.vo.ListVO;
import com.smart.project.web.vo.MainImageVO;
import com.smart.project.web.vo.PlaceVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PhotoAct {

    public final HomeService homeService;
    public final PhotoService photoService;
    public final PlaceService placeService;
    public final Place place;

//    final int[] PHOTO_HEIGHT = {200, 300, 600, 900};
//    final int[] PHOTO_WIDTH = {200, 400, 800, 1200};

    @RequestMapping("/photo")
    public String photo(Model model){
        List<ListVO> listVOS = homeService.selectAllList();
        model.addAttribute("list", listVOS);
        model.addAttribute("place", new PlaceVO());
        return "photo";
    }

    @RequestMapping("/upload")
    public String saveFile(@RequestParam("file") MultipartFile file, @RequestParam("category") int category, @ModelAttribute PlaceVO placeVO, HttpServletRequest request, InternCookie internCookie) throws Exception {

        String path = photoService.savePhoto(file, internCookie);
        placeVO.setListNum(category);
        int cnt = place.insertPlace(placeVO);
        if (cnt > 0){
            if (place.selectPlaceNum().size() > 0) {
                int placeNum = place.selectPlaceNum().get(0).getPlaceNum();
                MainImageVO vo = new MainImageVO(0, path, 0, placeNum);
                place.insertMainImage(vo);
            }
        }




        log.error("productVO : {}", placeVO);

        return "redirect:/admin";
    }

    @RequestMapping("/show")
    public void show(Model model, HttpServletRequest request) throws Exception {
        Map<String, String> cookieMap = ClientUtil.getCurrentCookie(request);
        String id = cookieMap.get("id");

        model.addAttribute("id", id);

    }

//    @RequestMapping("/seq")
//    public void seq(){
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String securePassword = encoder.encode("testtest");
//
//        log.error("encoding==>{}", securePassword);
//
//        boolean test = encoder.matches("testtest","testtest");
//        log.error("encoding==>{}", test);
//
//    }



}
