package com.smart.project.web.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ListVO {
    private int listNum;


    private String listName;
    private String listIntro;

    private String mainImageName;

    public ListVO() {
    }

    public ListVO(String listName, String listIntro) {
        this.listName = listName;
        this.listIntro = listIntro;
    }
}
