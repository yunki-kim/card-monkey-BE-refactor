package me.project.cardmonkeyrefactor.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Paid {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paid_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    public void setPaidMember(Member member) {
        this.member = member;
    }

    public void setPaidCard(Card card) {
        this.card = card;
    }

    public static Paid createPaid(Member member, Card card) {
        Paid paid = new Paid();

        paid.setPaidMember(member);
        paid.setPaidCard(card);

        return paid;
    }
}
