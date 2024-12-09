package com.tutorial.bikestores.production.brand;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandServiceImpl {
    private final BrandRepository brandRepository;
}