package com.springboot.autodrive.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record VeiculoRequestDTO(
        @NotBlank(message = "A marca é obrigatória")
        String marca,

        @NotBlank(message = "O modelo é obrigatório")
        String modelo,

        @NotNull(message = "O ano de fabricação é obrigatório")
        @Max(value = 9999, message = "Ano de fabricação inválido")
        Integer anoFabricacao,

        @NotNull(message = "O ano do modelo é obrigatório")
        @Max(value = 9999, message = "Ano do modelo inválido")
        Integer anoModelo,

        @NotBlank(message = "A placa é obrigatória")
        String placa,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        @Digits(integer = 10, fraction = 2, message = "O preço deve ter no máximo duas casas decimais")
        BigDecimal preco,

        String cor,

        @NotNull(message = "É necessário informar se o véiculo está disponível")
        Boolean disponivel
) {
}
