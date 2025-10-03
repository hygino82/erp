package br.dev.hygino.erp.services;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.erp.dto.RequestProductDto;
import br.dev.hygino.erp.dto.ResponseProductDto;
import br.dev.hygino.erp.models.Product;
import br.dev.hygino.erp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ResponseProductDto create(RequestProductDto dto) {
        Product product = new Product();
        dtoToEntity(dto, product);
        product.setCreatedAt(LocalDateTime.now());
        product = productRepository.save(product);

        return product.toResponseDto();
    }

    private void dtoToEntity(RequestProductDto dto, Product product) {
        product.setName(dto.name());
        product.setCategory(dto.category());
        product.setPrice(dto.price());
        product.setInStock(dto.inStock() == null ? 0 : dto.inStock());
        product.setBarcode(dto.barcode());
    }

    @Transactional
    public Page<ResponseProductDto> getAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(Product::toResponseDto);
    }
}
