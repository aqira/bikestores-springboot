package com.tutorial.bikestores.sales.customer;

import com.tutorial.bikestores.shared.validator.IndonesianPhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Integer id;
    @NotBlank
    @Length(min = 3, max = 50)
    private String firstName;
    @NotBlank
    @Length(min = 3, max = 50)
    private String lastName;
    @Length(min = 12, max = 14)
    @IndonesianPhoneNumber
    private String phone;
    @NotBlank
    @Email
    private String email;
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
