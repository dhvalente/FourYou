package br.com.foursys.fourcamp.fourbank.model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import br.com.foursys.fourcamp.fourbank.enums.PixKeyTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_pix")
public class Pix implements Serializable{
	private static final long serialVersionUID = 1L;
	
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

