package me.project.cardmonkeyrefactor.dto;

import lombok.*;
import me.project.cardmonkeyrefactor.entity.CardType;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardByBenefitResDTO {

    private Long id;
    private String name;
    private String company;
    private String image;
    private CardType type;
    private String benefit;
}
