package com.smart.project.web.vo;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MainImageVO {
    private int mainImageNum;
    private String mainImageName;
    @NonNull
    private int listNum;
    private int placeNum;

}
