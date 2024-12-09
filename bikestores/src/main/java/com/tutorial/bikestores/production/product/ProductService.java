package com.tutorial.bikestores.production.product;

import com.tutorial.bikestores.production.product.dto.ProductDetailsDTO;
import com.tutorial.bikestores.production.product.dto.ProductListDTO;
import com.tutorial.bikestores.production.product.dto.ProductSearchDTO;
import com.tutorial.bikestores.production.product.dto.ProductUpsertDTO;
import com.tutorial.bikestores.shared.DropdownDTO;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Page<ProductListDTO> findAll(ProductSearchDTO productSearchDTO);

    void saveProduct(ProductUpsertDTO productDTO);

    ProductUpsertDTO getProductById(Integer id);
    ProductDetailsDTO getProductDetailsById(Integer id);

    void deleteProductById(Integer id);

    List<DropdownDTO> findAllCategoryDropdowns();
    List<DropdownDTO> findAllBrandDropdowns();

}
