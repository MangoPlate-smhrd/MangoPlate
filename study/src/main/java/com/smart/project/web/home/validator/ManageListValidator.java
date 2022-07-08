package com.smart.project.web.home.validator;


import com.smart.project.web.vo.ListVO;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class ManageListValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ListVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ListVO listVO = (ListVO) target;

        if(!StringUtils.hasText(listVO.getListName())){
            //errors.put("listName", "이름은 필수입니다.");
            errors.rejectValue("listName", "required");
        }
        if(!StringUtils.hasText(listVO.getListIntro())){
            //errors.put("listIntro", "설명은 필수입니다.");
            //bindingResult.addError(new FieldError("listVO", "listIntro", listVO.getListIntro(), false, null, null, "설명은 필수입니다."));
            errors.rejectValue("listIntro", "required");
        }


    }
}
