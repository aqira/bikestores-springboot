package com.tutorial.bikestores.production.product;

import com.tutorial.bikestores.production.brand.Brand;
import com.tutorial.bikestores.production.brand.BrandRepository;
import com.tutorial.bikestores.production.category.Category;
import com.tutorial.bikestores.production.category.CategoryRepository;
import com.tutorial.bikestores.production.product.dto.ProductDetailsDTO;
import com.tutorial.bikestores.production.product.dto.ProductListDTO;
import com.tutorial.bikestores.production.product.dto.ProductSearchDTO;
import com.tutorial.bikestores.production.product.dto.ProductUpsertDTO;
import com.tutorial.bikestores.shared.DropdownDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final static Integer PAGE_SIZE = 10;

    @Override
    public Page<ProductListDTO> findAll(ProductSearchDTO productSearchDTO) {
        Pageable pageable = PageRequest.of(productSearchDTO.getPageNumber() - 1, PAGE_SIZE);
        return productRepository.findAll(
                productSearchDTO.getName(),
                productSearchDTO.getBrandId(),
                productSearchDTO.getCategoryId(),
                productSearchDTO.getModelYear(),
                productSearchDTO.getListPrice(),
                pageable);
    }

    @Override
    public void saveProduct(ProductUpsertDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        productRepository.save(product);
    }

    @Override
    public ProductUpsertDTO getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Product with that ID"));
        return modelMapper.map(product, ProductUpsertDTO.class);
    }

    @Override
    public ProductDetailsDTO getProductDetailsById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Product with that ID"));
        return modelMapper.map(product, ProductDetailsDTO.class);
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<DropdownDTO> findAllCategoryDropdowns() {
        return categoryRepository.findCategoriesDropdown();
    }

    @Override
    public List<DropdownDTO> findAllBrandDropdowns() {
        return brandRepository.findBrandsDropdown();
    }
}
