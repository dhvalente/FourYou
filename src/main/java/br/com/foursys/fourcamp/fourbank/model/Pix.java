package br.com.foursys.fourcamp.fourbank.model;



import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private PixKeyTypeEnum keyType;
    @NonNull
    private String keyContent;
    @NonNull
    private Boolean isActive;    

    @ManyToOne
    @JoinColumn( name = "tb_account", referencedColumnName = "id")
    private CheckingAccount account;
}

