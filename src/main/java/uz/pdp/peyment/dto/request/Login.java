package uz.pdp.peyment.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Login {
    private String username;
    private String password;
}
