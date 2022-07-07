package com.smart.project.web.home.act;

import com.smart.project.common.vo.InternCookie;
import com.smart.project.proc.Test;
import com.smart.project.util.ClientUtil;
import com.smart.project.util.CookieUtil;
import com.smart.project.web.home.biz.HomeService;
import com.smart.project.web.home.biz.MemberService;
import com.smart.project.web.home.vo.JoinVO;
import com.smart.project.web.vo.ListVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MangoAct {

    public final Test test;
    private final HomeService homeService;
    private final MemberService ms;


    @RequestMapping("/join")
    public void join() {

    }

    @RequestMapping("/joinComplete")
    public String joinComplete(@ModelAttribute JoinVO vo){
        if (vo != null) {
            int cnt = test.joinComplete(vo);
            log.error("join 성공 ==>{}", cnt + "");

            return "redirect:/";
        } else{
            return "redirect:/join";
        }
    }

    @RequestMapping("/login")
    public void login() {

    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        CookieUtil.deleteCookie(request, response, "id");
        Cookie[] cookies = request.getCookies(); // 모든 쿠키의 정보를 cookies에 저장

        if(cookies != null){ // 쿠키가 한개라도 있으면 실행

            for(int i=0; i< cookies.length; i++) {

                cookies[i].setMaxAge(0); // 유효시간을 0으로 설정

                response.addCookie(cookies[i]); // 응답 헤더에 추가
            }
        }
        return "redirect:/";

    }

    @RequestMapping("/loginComplete")
    public String loginComplete(JoinVO vo, HttpServletResponse res) throws IOException {


        JoinVO mvo = test.login(vo);
        if (mvo != null){
            log.error("로그인 성공");
            CookieUtil.createCookie(res, "id", mvo.getUserId());

            if (mvo.getUserManager().equals("1")){
                // UserManager가 1인 경우 = 관리자
                // UserManager가 0인 경우 = 일반 유저
                return "redirect:admin";
            }
            return "redirect:/";
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

    @RequestMapping("/kakao")
    public String kakao(){
        return "redirect:https://kauth.kakao.com/oauth/authorize?client_id=9b950cc68d2820a3e85047db80f55d96&redirect_uri=http://localhost/kakaoLogin&response_type=code";
    }

    @RequestMapping("/kakaoLogin")
    public String kakaoLogin(@RequestParam("code") String code, HttpServletResponse res) throws Exception {
        CookieUtil.createCookie(res, "id", "kakao");
        log.error("token==>{}", code);
        String access_Token = ms.getAccessToken(code);
        log.error("token2==>{}", access_Token);

        HashMap<String, Object> userInfo = ms.getUserInfo(access_Token);
        log.error("###access_Token#### : " + access_Token);
        log.error("###nickname#### : " + userInfo.get("nickname"));
        log.error("###email#### : " + userInfo.get("email"));

        return "redirect:/";

    }



}
