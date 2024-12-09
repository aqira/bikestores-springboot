package com.tutorial.bikestores.security.user;

import com.tutorial.bikestores.security.user.dto.UserCustomerRegistrationDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView customerLogin() {
        ModelAndView modelAndView = new ModelAndView("/customers/auth/customer-login");
        if (hasSignedIn()) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        return modelAndView;
    }

    @GetMapping("sign-up")
    public ModelAndView initCustomerRegistration() {
        ModelAndView modelAndView = new ModelAndView("/customers/auth/customer-registration");
        if (hasSignedIn()) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        modelAndView.addObject("registration", new UserCustomerRegistrationDTO());
        return modelAndView;
    }

    @PostMapping("sign-up")
    public String newCustomerRegistration(@Valid @ModelAttribute("registration")
                                          UserCustomerRegistrationDTO userCustomerRegistrationDTO,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/customers/auth/customer-registration";
        }
        userService.saveNewUserCustomer(userCustomerRegistrationDTO);
        return "redirect:/";
    }

    private boolean hasSignedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !authentication.getPrincipal().equals("anonymousUser");
    }

}
