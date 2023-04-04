package me.project.cardmonkeyrefactor.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginReqDTO {

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Pattern(regexp = "^[a-z0-9]{5,12}$", message = "5 ~ 12자 영문,숫자 조합을 입력해주세요.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^[a-z0-9]{8,12}$", message = "8~12자 영문,숫자 조합을 입력해주세요.")
    private String password;
}
