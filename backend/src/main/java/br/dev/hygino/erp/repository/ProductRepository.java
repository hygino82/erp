package br.dev.hygino.erp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.dev.hygino.erp.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT obj FROM Product obj WHERE obj.barcode = :barcode")
    Optional<Product> findByBarcode(String barcode);
}
