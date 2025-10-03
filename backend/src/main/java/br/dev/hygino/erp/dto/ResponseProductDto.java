package br.dev.hygino.erp.dto;

import java.time.LocalDateTime;

public record ResponseProductDto(
                Long id,
                String name,
                String category,
                Double price,
                Integer inStock,
                String barcode,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {

}
