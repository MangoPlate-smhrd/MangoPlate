package com.smart.project.proc;

import com.smart.project.annotation.Master;
import com.smart.project.web.home.vo.JoinVO;
import com.smart.project.web.vo.ListVO;
import com.smart.project.web.vo.MainImageVO;
import com.smart.project.web.vo.PlaceVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Master
@Component
public interface Test {
	/**********************************************************************************************
	 * @Method 설명 : Test_Mapper.xml에 있는 쿼리를 사용 할 경우
	 * @작성일 : 2021-02-15
	 * @작성자 : 김남현
	 * @변경이력 :
	 **********************************************************************************************/
	int joinComplete(JoinVO vo);

	JoinVO login(JoinVO vo);

	List<ListVO> selectAllList();

	ListVO selectList(int num);

	List<PlaceVO> selectAllCategoryProduct(int num);
	PlaceVO selectProduct(int num);

	int insertPlace(PlaceVO placeVO);

	void insertList(ListVO listVO);
	void insertMainImage(MainImageVO mainImageVO);
	void deleteMainImage(int num);
	void deleteList(int num);
	void updateList(ListVO listVO);
	void updateMainImage(MainImageVO mainImageVO);
	MainImageVO selectMainImageListNum(int listNum);



}
