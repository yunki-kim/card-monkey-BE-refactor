package me.project.cardmonkeyrefactor.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginReqDTO {

    private String userId;
    private String password;
}
