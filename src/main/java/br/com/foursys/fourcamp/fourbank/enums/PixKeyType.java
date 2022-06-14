package com.fourcamp.fourcamp22.java.group4.enums;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PixKeyType {

    CPF(0),
    EMAIL(1),
    PHONE(2),
    RANDOM(3);

    private Integer memberName;

    private Integer memberName() {
        return this.memberName;
    }

}
