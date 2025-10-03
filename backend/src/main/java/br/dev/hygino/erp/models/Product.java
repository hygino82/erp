package br.dev.hygino.erp.models;

import java.time.LocalDateTime;

import br.dev.hygino.erp.dto.ResponseProductDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String category;

    @NotNull
    @PositiveOrZero
    private Double price;

    @PositiveOrZero
    private Integer inStock;

    @Column(length = 14, unique = true)
    private String barcode;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ResponseProductDto toResponseDto() {
        return new ResponseProductDto(id, name, category, price, inStock, barcode, createdAt, updatedAt);
    }
}
