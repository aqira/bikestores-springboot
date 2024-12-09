package com.tutorial.bikestores.production.category;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Integer id;

    @NotBlank(message = "kalo name jangan kosong!")
    @Length(min = 3, max = 50, message = "panjangnya jangan dibawah 3 ato diatas 50")
    private String name;
}
