package me.project.cardmonkeyrefactor.dto;

import lombok.*;
import me.project.cardmonkeyrefactor.entity.Benefit;
import me.project.cardmonkeyrefactor.entity.Member;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupReqDTO {

    private String userId;
    private String password;
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
