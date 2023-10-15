package uz.pdp.peyment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionCreateDto {
    private String receiverCardNum;
    private String senderCardNum;
    private Double amount;

}
