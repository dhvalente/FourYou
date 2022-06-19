package br.com.foursys.fourcamp.fourbank.model;

import br.com.foursys.fourcamp.fourbank.enums.PaymentTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_payments")
public class Transaction implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id", nullable = false)
    private Long id;
    @NonNull
    private PaymentTypeEnum type;
    @NonNull
    private String identifier;
    @ManyToOne
    @JoinColumn(name = "account_id")
    @NonNull
    private Account originAccount;
    @ManyToOne
    @JoinColumn(name = "destination_account_id")
    @NonNull
    private Account destinationAccount;
    @NonNull
    private Double value;

    private Integer numberOfInstallments;
    private LocalDate date;
}
