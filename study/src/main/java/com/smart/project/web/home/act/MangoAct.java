package com.smart.project.web.home.act;

import com.smart.project.proc.Test;
import com.smart.project.util.ClientUtil;
import com.smart.project.util.CookieUtil;
import com.smart.project.web.home.biz.HomeService;
import com.smart.project.web.home.vo.JoinVO;
import com.smart.project.web.vo.ListVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MangoAct {

    public final Test test;
    private final HomeService homeService;

    @RequestMapping("/index")
    public void index() {

    }

    @RequestMapping("/main")
    public void main(HttpServletRequest request, Model model) throws Exception {
        List<ListVO> lists = homeService.selectList();
        model.addAttribute("list", lists);
        log.error("homePage");
        Map<String, String> cookieMap = ClientUtil.getCurrentCookie(request);
        String id = cookieMap.get("id");
        log.error("main id ==> {}", id);

        model.addAttribute("login", id);

    }

    @RequestMapping("/join")
    public void join() {

    }

    @RequestMapping("/joinComplete")
    public String joinComplete(@ModelAttribute JoinVO vo){
        int cnt = test.joinComplete(vo);
        log.error("join 성공 ==>{}", cnt+"");

        return "redirect:main";
    }

    @RequestMapping("/login")
    public void login() {

    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        CookieUtil.deleteCookie(request, response, "id");

        return "redirect:main";

    }

    @RequestMapping("/loginComplete")
    public String loginComplete(JoinVO vo, HttpServletResponse res){
        JoinVO mvo = test.login(vo);

        if (mvo.getUserId() != null){
            log.error("로그인 성공");
            CookieUtil.createCookie(res, "id", mvo.getUserId());


            if (mvo.getUserManager().equals("1")){
                // UserManager가 1인 경우 = 관리자
                // UserManager가 0인 경우 = 일반 유저
                return "redirect:admin";
            }

            return "redirect:main";
        } else {
            log.error("로그인 실패");
            return "redirect:login";
        }
    }


    @RequestMapping("/admin")
    public void admin(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        log.error("Today={}",strToday);

    }


}
