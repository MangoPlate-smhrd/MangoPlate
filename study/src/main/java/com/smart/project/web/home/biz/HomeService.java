package com.smart.project.web.home.biz;

import com.smart.project.proc.Test;
import com.smart.project.web.vo.ListVO;
import com.smart.project.web.vo.MainImageVO;
import com.smart.project.web.vo.PlaceVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
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

    public ListVO selectList(int num){
        ListVO listVO = test.selectList(num);
        if(listVO.getMainImageName() != null){
            String mainImageName = listVO.getMainImageName();
            if(mainImageName.split("_")[0].length() == 8){
                String s = mainImageName.split("_")[0];
                String path = File.separator + s.substring(0,4) + File.separator + s.substring(4, 6) + File.separator + s.substring(6, 8);
                listVO.setMainImageName("/image"+path + File.separator + mainImageName);
            }
        }
        return listVO;
    }

    public List<PlaceVO> selectAllCategoryProduct(int num){
        List<PlaceVO> vo = test.selectAllCategoryProduct(num);
        return vo;
    }

    public void insertList(ListVO listVO){
        test.insertList(listVO);
    }

    public void insertMainImage(ListVO listVO){
        test.insertMainImage(listVO);
    }

    public void deleteMainImage(int num){
        test.deleteMainImage(num);
    }

    public void deleteList(int num){
        test.deleteList(num);
    }

    public void updateList(ListVO listVO){
        test.updateList(listVO);
    }

    public void updateMainImage(MainImageVO mainImageVO){
        test.updateMainImage(mainImageVO);
    }

    public String selectMainImageNameListNum(int listNum){
        MainImageVO mainImageVO = test.selectMainImageListNum(listNum);
        return mainImageVO.getMainImageName();
    }



    public PlaceVO selectProduct(int num){
        PlaceVO vo = test.selectProduct(num);
        return vo;
    }



}
