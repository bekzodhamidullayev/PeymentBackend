package uz.pdp.peyment.dto.respons;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionResponseDto {
    private Double balance;
    private String receiverCardNum;
    private String senderCardNum;
}
