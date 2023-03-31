package me.project.cardmonkeyrefactor.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardCountResDTO {

    private List<CardResDTO> content;
    private long totalElements;
}
