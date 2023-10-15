package uz.pdp.peyment.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.peyment.entity.enums.CardType;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardCreateDto {


    private UUID ownerId;
    @Min(value = 1000, message = "card number length should be at least 4")
    @Max(value = 9999, message = "card number length should be at least 4")
    private String cardNumber;
    @NotBlank(message = "password cannot be empty or blank")
    private String password;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
}
