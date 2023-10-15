package uz.pdp.peyment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionInPeriod {
    private UUID ownerId;
    private LocalDateTime firstTime;
    private LocalDateTime  lastTime;
}
