package com.smart.project.web.vo;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@RequiredArgsConstructor
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
