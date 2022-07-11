package com.smart.project.web.home.validate.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ListVOUpdateForm {
    @NotBlank
    private String listName;
    @NotBlank
    private String listIntro;
}
