package uz.pdp.peyment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import uz.pdp.peyment.entity.enums.CardType;

import java.util.List;
@Entity(name = "card")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CardEntity extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "ownerId", referencedColumnName = "id")
    private UserEntity owner;
    private boolean isActive = true;
    @Column(unique = true, nullable = false)
    private String cardNumber;
    private String password;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private CardType type;
    @OneToMany
    @JsonIgnore
    private List<TransactionEntity> transactionEntities;
}
