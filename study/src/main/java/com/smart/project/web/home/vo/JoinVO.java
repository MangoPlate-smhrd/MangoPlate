package com.smart.project.web.home.vo;

import lombok.*;
import org.springframework.context.annotation.Primary;

import javax.annotation.Generated;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class JoinVO {
    int userNum;
    @NonNull
    String userId;
    @NonNull
    String userPw;
    String userName;
    String userSignUp;
    String userManager;

}
