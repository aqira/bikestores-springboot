package com.tutorial.bikestores.clients;

import com.tutorial.bikestores.clients.dto.*;
import com.tutorial.bikestores.shared.DropdownDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {
    Page<ProductCatalogueDTO> getProductCatalogues(ProductCatalogueSearchDTO searchDTO);
    Page<ProductCatalogueDTO> getProductCatalogues(int pageNumber);
    ProductCatalogueDTO getProductCatalogueById(int id);
    void addToCart(AddToCartDTO addToCartDTO);
    List<DropdownDTO> findAllStoreDropdowns(Integer productId);
    List<CartDTO> getCustomerCartsByUsername(String username);
    CartDTO getCustomerCartByStoreId(String username, Integer storeId);
    void orderProducts(OrderCartDTO orderCartDTO);
}
