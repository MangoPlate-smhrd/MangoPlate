package com.smart.project.web.home.biz;

import com.smart.project.proc.PlaceRepository;
import com.smart.project.proc.Test;
import com.smart.project.web.vo.PlaceVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public List<PlaceVO> selectAllPlace(){
        return placeRepository.selectAllPlace();
    }

    public List<PlaceVO> selectAllPlaceJoinMainImageName(){
        return placeRepository.selectAllPlaceJoinMainImageName();
    }
}
