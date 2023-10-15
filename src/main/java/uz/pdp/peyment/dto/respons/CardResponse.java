package uz.pdp.peyment.dto.respons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.peyment.entity.enums.CardType;
import uz.pdp.peyment.entity.UserEntity;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardResponse {

    private UUID ownerId;
    private UUID id;
    private String cardNumber;
    private Double balance;
    private CardType type;
}
