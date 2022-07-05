package com.smart.project.web.home.biz;

import com.smart.project.proc.Test;
import com.smart.project.web.vo.PlaceVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceService {
    private final Test test;

    public void insertPlace(PlaceVO placeVO){
        test.insertPlace(placeVO);
    }
}
