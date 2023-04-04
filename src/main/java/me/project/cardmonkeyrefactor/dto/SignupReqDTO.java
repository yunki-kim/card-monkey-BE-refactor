package me.project.cardmonkeyrefactor.dto;

import lombok.*;
import me.project.cardmonkeyrefactor.entity.Benefit;
import me.project.cardmonkeyrefactor.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupReqDTO {

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Pattern(regexp = "^[a-z0-9]{5,12}$", message = "5 ~ 12자 영문,숫자 조합을 입력해주세요.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^[a-z0-9]{8,12}$", message = "8~12자 영문,숫자 조합을 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    @Pattern(regexp = "^[가-힣]{2,6}$", message = "2~6자 한글을 입력해주세요.")
    private String name;

    private String role;
    private List<String> benefit;

    public Member toEntity(List<String> benefits) {
        return Member.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .role(role)
                .benefit(new Benefit(benefits))
                .build();
    }
}
