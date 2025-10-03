package br.dev.hygino.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.hygino.erp.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
