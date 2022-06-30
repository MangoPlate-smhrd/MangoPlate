package com.smart.project.web.home.biz;

import com.smart.project.proc.Test;
import com.smart.project.web.vo.ListVO;
import com.smart.project.web.vo.PlaceVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeService {
    private final Test test;

    public List<ListVO> selectList(){
        List<ListVO> vo = test.selectList();
        return vo;
    }

    public List<PlaceVO> selectAllPlace(int num){
        List<PlaceVO> vo = test.selectAllPlace(num);
        return vo;
    }

    public PlaceVO selectPlace(int num){
        PlaceVO vo = test.selectPlace(num);
        return vo;
    }


}