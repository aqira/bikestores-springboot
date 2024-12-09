package com.tutorial.bikestores.security.user;

import com.tutorial.bikestores.sales.customer.Customer;
import com.tutorial.bikestores.sales.customer.CustomerRepository;
import com.tutorial.bikestores.security.role.Role;
import com.tutorial.bikestores.security.user.dto.UserCustomerRegistrationDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void saveNewUserCustomer(UserCustomerRegistrationDTO userCustomerRegistrationDTO) {
        if (!userCustomerRegistrationDTO.getPassword().equals(userCustomerRegistrationDTO.getRetypePassword())) {
            throw new InputMismatchException("The first and second Password does not match");
        }
        if (userRepository.existsById(userCustomerRegistrationDTO.getUsername())) {
            throw new EntityExistsException("The username is already taken");
        }
        User user = modelMapper.map(userCustomerRegistrationDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(new Role(3));
        userRepository.save(user);

        Customer customer = modelMapper.map(userCustomerRegistrationDTO, Customer.class);
        customerRepository.save(customer);

    }
}
