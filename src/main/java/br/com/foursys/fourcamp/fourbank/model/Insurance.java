package br.com.foursys.fourcamp.fourbank.model;

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
@Table(name="tb_insurance")
public class Insurance {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    private String rules;

    public void registerInsurance(String name, String rules) {
        //todo
    }


}
