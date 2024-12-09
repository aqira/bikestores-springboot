package com.tutorial.bikestores.sales.customer;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/customer-registration")
    public ModelAndView initForm() {
        ModelAndView modelAndView = new ModelAndView("customers/register-customer");
        modelAndView.addObject("customer", new CustomerDTO());
        return modelAndView;
    }

    @PostMapping("/customer-registration")
    public String creationalForm(@Valid @ModelAttribute("customer") CustomerDTO customerDTO,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customers/register-customer";
        }
        customerService.saveCustomer(customerDTO);
        return "redirect:/customer-registration";
    }
}
