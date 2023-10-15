package uz.pdp.peyment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCardDto {
    private UUID ownerId;
    private String cardNumber;
    private Double amount;
}
