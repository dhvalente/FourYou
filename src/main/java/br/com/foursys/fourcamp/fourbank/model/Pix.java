package br.com.foursys.fourcamp.fourbank.model;

import br.com.foursys.fourcamp.fourbank.enums.PixKeyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

    private Double value;

    private LocalDateTime date;

    private String keyContent;

    private Boolean isActive;

    private Account account;

    public Boolean activateKey(PixKeyType keyType, String keyContent, Boolean isActive) {
        //todo
        return true;
    }
}
