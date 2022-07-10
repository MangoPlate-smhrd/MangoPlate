package com.smart.project.web.vo;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class MainImageVO {
    private int mainImageNum;
    private String mainImageName;
    @NonNull
    private int listNum;
    private int placeNum;

}
