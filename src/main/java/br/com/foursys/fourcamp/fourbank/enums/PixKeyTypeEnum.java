package br.com.foursys.fourcamp.fourbank.enums;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PixKeyTypeEnum {

    CPF(0),
    EMAIL(1),
    PHONE(2),
    RANDOM(3);

    private Integer memberName;

    private Integer memberName() {
        return this.memberName;
    }

}
