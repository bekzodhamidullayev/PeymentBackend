package uz.pdp.peyment.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateDto {
    private String name;
    @NotBlank(message = "username cannot be empty or blank")
    private String username;
    @NotBlank(message = "password cannot be empty or blank")
    @Min(value = 4, message = "password length should be at least 4")
    private String password;
}
