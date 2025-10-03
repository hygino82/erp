package br.dev.hygino.erp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record RequestProductDto(
        @NotBlank @Size(max = 50) String name,

        @NotBlank @Size(max = 50) String category,

        @NotNull @PositiveOrZero Double price,

        @PositiveOrZero Integer inStock,

        @NotBlank @Size(max = 14) String barcode) {

}
