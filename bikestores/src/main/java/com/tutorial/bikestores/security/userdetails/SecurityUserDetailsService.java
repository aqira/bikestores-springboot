package com.tutorial.bikestores.security.userdetails;

import com.tutorial.bikestores.security.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SecurityUserDetails(
                userRepository.findById(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Username tidak ditemukan"))
        );
    }
}