package com.springboot.autodrive.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "veiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column (name = "ano_fabricacao", nullable = false)
    private Integer anoFabricacao;

    @Column (name = "ano_modelo", nullable = false)
    private Integer anoModelo;

    @Column (nullable = false, unique = true)
    private String placa;

    @Column (nullable = false, precision = 12, scale = 2)
    private BigDecimal preco;

    private String cor;

    @Column (nullable = false)
    private Boolean disponivel;

}
