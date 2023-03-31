package me.project.cardmonkeyrefactor.dto;

import lombok.*;
import me.project.cardmonkeyrefactor.entity.Card;
import me.project.cardmonkeyrefactor.entity.CardType;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardByFavorResDTO {

    private Long id;
    private String name;
    private String company;
    private String image;
    private CardType type;
    private int favor;

    public CardByFavorResDTO(Card card, int favor) {
        this.id = card.getId();
        this.name = card.getName();
        this.company = card.getCompany();
        this.image = card.getImageURL();
        this.type = card.getCardType();
        this.favor = favor;
    }
}
