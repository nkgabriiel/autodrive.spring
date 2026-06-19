package com.springboot.autodrive.dto;

import com.springboot.autodrive.model.Veiculo;

import java.math.BigDecimal;

public record VeiculoResponseDTO(
        Long id,
        String marca,
        String modelo,
        Integer anoFabricacao,
        Integer anoModelo,
        String placa,
        BigDecimal preco,
        String cor,
        Boolean disponivel

) {

    public static VeiculoResponseDTO fromEntity(Veiculo veiculo) {
        return new VeiculoResponseDTO(
                veiculo.getId(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getAnoFabricacao(),
                veiculo.getAnoModelo(),
                veiculo.getPlaca(),
                veiculo.getPreco(),
                veiculo.getCor(),
                veiculo.getDisponivel()
        );
    }
}
