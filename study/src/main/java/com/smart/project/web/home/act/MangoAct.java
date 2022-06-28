package com.smart.project.web.home.act;

import com.smart.project.proc.Test;
import com.smart.project.web.home.vo.JoinVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MangoAct {

    public final Test test;

    @RequestMapping("/index")
    public void index() {

    }

    @RequestMapping("/generic")
    public void test() {

    }
    @RequestMapping("/main")
    public void main() {

    }

    @RequestMapping("/elements")
    public void elements() {

    }

    @RequestMapping("/join")
    public void join() {

    }

    @RequestMapping("/joinComplete")
    public String joinComplete(@ModelAttribute JoinVO vo){
        int cnt = test.joinComplete(vo);
        log.error("join 성공 ==>{}", cnt+"");

        return "main";
    }




}
