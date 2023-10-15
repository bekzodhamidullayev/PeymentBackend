package uz.pdp.peyment.dto.respons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserResponseDTO {
    private UUID id;
    private String name;
    private String username;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
