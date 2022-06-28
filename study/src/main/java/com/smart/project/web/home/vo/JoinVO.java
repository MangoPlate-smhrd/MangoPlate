package com.smart.project.web.home.vo;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class JoinVO {
    int user_num;
    @NonNull
    String user_id;
    @NonNull
    String user_pw;
    @NonNull
    String user_name;
    String user_signUp;
    String user_manager;
}
