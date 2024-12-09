package com.tutorial.bikestores.clients;

import com.tutorial.bikestores.clients.dto.AddToCartDTO;
import com.tutorial.bikestores.clients.dto.OrderCartDTO;
import com.tutorial.bikestores.clients.dto.ProductCatalogueDTO;
import com.tutorial.bikestores.shared.DropdownDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @ModelAttribute("storeDropdowns")
    List<DropdownDTO> storeDropdowns(@PathVariable(name = "id", required = false) Integer productId) {
        return clientService.findAllStoreDropdowns(productId);
    }

    @ModelAttribute("signedIn")
    boolean isSignedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !authentication.getPrincipal().equals("anonymousUser");
    }

    @ModelAttribute("username")
    String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/")
    public ModelAndView showHome(@RequestParam(defaultValue = "1") Integer pageNumber) {
        ModelAndView mv = new ModelAndView("/clients/index");
        Page<ProductCatalogueDTO> productCatalogues = clientService.getProductCatalogues(pageNumber);
        mv.addObject("productCatalogues", productCatalogues);
        mv.addObject("currentPage", pageNumber);

        return mv;
    }

    @GetMapping("/store-product/{id}")
    public ModelAndView showProductDetails(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView("/clients/store-product-details");
        mv.addObject("productCatalogue", clientService.getProductCatalogueById(id));
        mv.addObject("cart", new AddToCartDTO());
        return mv;
    }

    @PostMapping("/store-product/{id}")
    public String addProductToCart(@PathVariable Integer id, @ModelAttribute("cart") AddToCartDTO cartDTO) {
        cartDTO.setUsername(getUsername());
        cartDTO.setProductId(id);

        clientService.addToCart(cartDTO);
        return "redirect:/store-product/" + id;
    }

    @GetMapping("/cart")
    public ModelAndView showCarts() {
        ModelAndView mv = new ModelAndView("/clients/cart");
        mv.addObject("carts", clientService.getCustomerCartsByUsername(getUsername()));
        return mv;
    }

    @GetMapping("/cart/{storeId}")
    public ModelAndView showCartCheckoutDetails(@PathVariable Integer storeId) {
        ModelAndView mv = new ModelAndView("/clients/cart-checkout");
        mv.addObject("cart", clientService.getCustomerCartByStoreId(getUsername(), storeId));
        mv.addObject("order", new OrderCartDTO());
        return mv;
    }

    @PostMapping("/cart/{storeId}")
    public String orderProductsFromCart(OrderCartDTO orderCartDTO) {
        orderCartDTO.setUsername(getUsername());
        clientService.orderProducts(orderCartDTO);
        return "redirect:/cart";
    }
}
