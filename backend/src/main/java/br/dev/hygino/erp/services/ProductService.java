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
import jakarta.persistence.EntityNotFoundException;
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

    @Transactional(readOnly = true)
    public Page<ResponseProductDto> getAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(Product::toResponseDto);
    }

    @Transactional
    public ResponseProductDto getByBarcode(String barcode) {
        Product result = productRepository.findByBarcode(barcode)
                .orElseThrow(() -> new IllegalArgumentException("Product with barcode " + barcode + " not found"));
        return result.toResponseDto();
    }

    @Transactional
    public ResponseProductDto update(Long id, RequestProductDto dto) {
        try {
            Product product = productRepository.getReferenceById(id);
            dtoToEntity(dto, product);
            product.setUpdatedAt(LocalDateTime.now());
            product = productRepository.save(product);
            return product.toResponseDto();
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }
    }

    @Transactional
    public ResponseProductDto updateProductAmount(Long id, Integer amount) {
        try {
            Product product = productRepository.getReferenceById(id);

            if (product.getInStock() + amount < 0) {
                throw new IllegalArgumentException("Insufficient stock for product with id " + id);
            }

            product.setInStock(product.getInStock() + amount);
            product.setUpdatedAt(LocalDateTime.now());
            product = productRepository.save(product);
            return product.toResponseDto();
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }
    }

    public void deleteById(Long id) {
        try {
            Product product = productRepository.getReferenceById(id);
            productRepository.delete(product);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }
    }
}
