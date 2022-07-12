package com.smart.project.web.vo;


import lombok.*;
import org.springframework.lang.Nullable;


@Data
@NoArgsConstructor
public class MainImageVO {
    private int mainImageNum;
    private String mainImageName;
    @Nullable
    private int listNum;
    @Nullable
    private int placeNum;

    public MainImageVO(int listNum) {
        this.listNum = listNum;
    }
}
