package com.fourcamp.fourcamp22.java.group4.model;

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
@Table(name="tb_insurance")
public class Insurance {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String rules;

    public void registerInsurance(String name, String rules) {
        //todo
    }


}
