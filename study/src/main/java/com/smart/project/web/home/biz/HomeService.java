package com.smart.project.web.home.biz;

import com.smart.project.proc.Test;
import com.smart.project.web.vo.ListVO;
import com.smart.project.web.vo.PlaceVO;
import javafx.scene.input.DataFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeService {
    private final Test test;

    public List<ListVO> selectAllList(){
        List<ListVO> vo = test.selectAllList();
        if(vo != null){
            for (ListVO listVO :
                    vo) {
                if(listVO.getMainImageName() != null){
                    String mainImageName = listVO.getMainImageName();
                    if(mainImageName.split("_")[0].length() == 8){
                        String s = mainImageName.split("_")[0];
                        String path = File.separator + s.substring(0,4) + File.separator + s.substring(4, 6) + File.separator + s.substring(6, 8);
                        listVO.setMainImageName("/image"+path + File.separator + mainImageName);
                    }
                }

            }
        }
        return vo;
    }

    public List<PlaceVO> selectAllPlace(int num){
        List<PlaceVO> vo = test.selectAllPlace(num);
        return vo;
    }

    public void insertList(ListVO listVO){
        test.insertList(listVO);
    }

    public PlaceVO selectPlace(int num){
        PlaceVO vo = test.selectPlace(num);
        return vo;
    }


}
