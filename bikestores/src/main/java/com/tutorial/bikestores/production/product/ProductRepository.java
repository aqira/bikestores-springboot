package com.tutorial.bikestores.production.product;

import com.tutorial.bikestores.clients.dto.ProductCatalogueDTO;
import com.tutorial.bikestores.production.product.dto.ProductListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("""
            SELECT new com.tutorial.bikestores.production.product.dto.ProductListDTO(
            p.id,
            p.name,
            p.brand.name,
            p.category.name,
            p.modelYear,
            p.listPrice
            )
            FROM Product p
            WHERE (:name IS NULL OR p.name LIKE %:name%)
            AND (:brandId IS NULL OR p.brand.id = :brandId)
            AND (:categoryId IS NULL OR p.category.id = :categoryId)
            AND (:modelYear IS NULL OR p.modelYear = :modelYear)
            AND (:listPrice IS NULL OR p.listPrice = :listPrice)
            """)
    Page<ProductListDTO> findAll(@Param("name") String name,
                                 @Param("brandId") Integer brandId,
                                 @Param("categoryId") Integer categoryId,
                                 @Param("modelYear") Integer modelYear,
                                 @Param("listPrice") BigDecimal listPrice,
                                 Pageable pageable);

    @Query("""
            SELECT new com.tutorial.bikestores.clients.dto.ProductCatalogueDTO(
            p.id,
            p.name,
            p.brand.name,
            p.category.name,
            p.modelYear,
            p.listPrice
            )
            FROM Product p
            WHERE (:name IS NULL OR p.name LIKE %:name%)
            AND (:brandId IS NULL OR p.brand.id = :brandId)
            AND (:categoryId IS NULL OR p.category.id = :categoryId)
            AND (:modelYear IS NULL OR p.modelYear = :modelYear)
            AND (:listPrice IS NULL OR p.listPrice = :listPrice)
            """)
    Page<ProductCatalogueDTO> findProductCatalogues(@Param("name") String name,
                                                    @Param("brandId") Integer brandId,
                                                    @Param("categoryId") Integer categoryId,
                                                    @Param("modelYear") Integer modelYear,
                                                    @Param("listPrice") BigDecimal listPrice,
                                                    Pageable pageable);

    @Query("""
            SELECT new com.tutorial.bikestores.clients.dto.ProductCatalogueDTO(
            p.id,
            p.name,
            p.brand.name,
            p.category.name,
            p.modelYear,
            p.listPrice
            )
            FROM Product p
            """)
    Page<ProductCatalogueDTO> findProductCatalogues(Pageable pageable);

    @Query("""
            SELECT new com.tutorial.bikestores.clients.dto.ProductCatalogueDTO(
            p.id,
            p.name,
            p.brand.name,
            p.category.name,
            p.modelYear,
            p.listPrice
            )
            FROM Product p
            WHERE p.id = :id
            """)
    ProductCatalogueDTO findProductCatalogueById(@Param("id") Integer id);
}
