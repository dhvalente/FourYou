package com.fourcamp.fourcamp22.java.group4.model;

import com.fourcamp.fourcamp22.java.group4.enums.PixKeyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private PixKeyType keyType;

    private String keyContent;

    private Boolean isActive;
}
