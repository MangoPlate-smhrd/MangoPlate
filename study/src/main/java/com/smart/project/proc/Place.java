package com.smart.project.proc;

import com.smart.project.annotation.Master;
import com.smart.project.web.vo.MainImageVO;
import com.smart.project.web.vo.PlaceVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Master
@Component
public interface Place {
    /**********************************************************************************************
     * @Method 설명 : Product_Mapper.xml에 있는 쿼리를 사용 할 경우
     * @작성일 : 2022-07-08
     * @작성자 : 정우철
     * @변경이력 :
     **********************************************************************************************/
    List<PlaceVO> selectAllPlace();

    int insertMainImage(MainImageVO vo);

    int insertPlace(PlaceVO vo);

    List<PlaceVO> selectPlaceNum();


}
