package me.project.cardmonkeyrefactor.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    private String role;

    @Embedded
    private Benefit benefit;

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void updateBenefit(Benefit newBenefit) {
        this.benefit = newBenefit;
    }
}
