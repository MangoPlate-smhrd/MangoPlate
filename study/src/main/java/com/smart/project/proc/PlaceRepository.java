package com.smart.project.proc;

import com.smart.project.annotation.Master;
import com.smart.project.web.vo.PlaceVO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Master
@Repository
public interface PlaceRepository {
    List<PlaceVO> selectAllPlace();

    List<PlaceVO> selectAllPlaceJoinMainImageName();
}
