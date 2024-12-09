package com.tutorial.bikestores.security.user;

import com.tutorial.bikestores.security.user.dto.UserCustomerRegistrationDTO;

public interface UserService {
    void saveNewUserCustomer(UserCustomerRegistrationDTO userCustomerRegistrationDTO);

}
