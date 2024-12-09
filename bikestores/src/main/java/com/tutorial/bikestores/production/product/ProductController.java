package com.tutorial.bikestores.production.product;

import com.tutorial.bikestores.production.product.dto.ProductListDTO;
import com.tutorial.bikestores.production.product.dto.ProductSearchDTO;
import com.tutorial.bikestores.production.product.dto.ProductUpsertDTO;
import com.tutorial.bikestores.shared.DropdownDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ModelAttribute("categoryDropdowns")
    public List<DropdownDTO> showCategoryDropdowns() {
        return productService.findAllCategoryDropdowns();
    }

    @ModelAttribute("brandDropdowns")
    public List<DropdownDTO> showBrandDropdowns() {
        return productService.findAllBrandDropdowns();
    }

    @GetMapping("/products")
    public ModelAndView showListOfProducts(@ModelAttribute("search") ProductSearchDTO productSearchDTO) {

        ModelAndView modelAndView = new ModelAndView("products/products-index");
        Page<ProductListDTO> products = productService.findAll(productSearchDTO);
        modelAndView.addObject("products", products);

        return modelAndView;
    }

    @GetMapping("/products/{id}")
    public ModelAndView findProduct(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("products/product-details");
        modelAndView.addObject("product", productService.getProductDetailsById(id));
        return modelAndView;
    }

    @GetMapping("/products/new")
    public ModelAndView initProductCreationalForm() {
        ModelAndView modelAndView = new ModelAndView("products/upsert-product");
        modelAndView.addObject("product", new ProductUpsertDTO());
        return modelAndView;
    }

    @PostMapping("/products/new")
    public String createProduct(@Valid @ModelAttribute ProductUpsertDTO product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/products/new";
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/edit")
    public ModelAndView initProductUpdateForm(@PathVariable Integer id) {
        ProductUpsertDTO productDTO = productService.getProductById(id);

        ModelAndView modelAndView = new ModelAndView("products/upsert-product");
        modelAndView.addObject("product", productDTO);
        return modelAndView;
    }

    @PostMapping("/products/{productId}/edit")
    public String updateProduct(@Valid @ModelAttribute ProductUpsertDTO product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/remove")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }
}
