package com.smart.project.web.home.act;

import com.smart.project.util.ClientUtil;
import com.smart.project.util.ImageUtil;
import com.smart.project.web.home.biz.HomeService;
import com.smart.project.web.home.biz.PhotoService;
import com.smart.project.web.home.biz.PlaceService;
import com.smart.project.web.vo.ListVO;
import com.smart.project.web.vo.PlaceVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PhotoAct {

    public final HomeService homeService;
    public final PhotoService photoService;
    public final PlaceService placeService;

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
    public String saveFile(@RequestParam("file") MultipartFile file, @RequestParam("category") int category,@ModelAttribute PlaceVO placeVO, HttpServletRequest request) throws Exception {

        String path = photoService.savePhoto(file, "places_img");
        placeVO.setListNum(category);
        placeVO.setPlaceMainImage(path);
        placeService.insertPlace(placeVO);
        log.error("placeVO : {}", placeVO);

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
