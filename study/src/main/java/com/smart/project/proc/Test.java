package com.smart.project.proc;

import com.smart.project.annotation.Master;
import com.smart.project.common.vo.MenuVO;
import com.smart.project.web.home.vo.JoinVO;
import com.smart.project.web.home.vo.StudyTestVO;
import com.smart.project.web.home.vo.TestVO;
import com.smart.project.web.vo.ListVO;
import com.smart.project.web.vo.PlaceVO;
import org.apache.ibatis.annotations.SelectProvider;
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

	List<PlaceVO> selectAllPlace(int num);
	PlaceVO selectPlace(int num);

	void insertPlace(PlaceVO placeVO);

	void insertList(ListVO listVO);


}
