package com.fourcamp.fourbank.model;


import com.fourcamp.fourbank.enums.PixKeyTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_pix")
public class Pix {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @NonNull
    private PixKeyTypeEnum keyType;
    @NonNull
    private String keyContent;
    @NonNull
    private Boolean isActive;
}
