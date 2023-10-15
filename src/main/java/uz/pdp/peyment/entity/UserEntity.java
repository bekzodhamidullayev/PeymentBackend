package uz.pdp.peyment.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity extends BaseEntity{
    private String name;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;

}
