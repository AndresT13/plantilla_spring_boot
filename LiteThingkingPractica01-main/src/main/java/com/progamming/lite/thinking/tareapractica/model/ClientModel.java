package com.progamming.lite.thinking.tareapractica.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="t_clientes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 3)
    private String documentType;
    @Column(length = 8)
    private Integer numberDocument;
    @Column(length = 100)
    @NotNull
    @NotBlank
    private String firstName;
    @Column(length = 100)
    @NotNull
    @NotBlank
    private String secondName;
    @Column(length = 50)
    private String numberPhone;
    @Column(length = 160)
    private String address;
    @Column(length = 60)
    private String city;
}
