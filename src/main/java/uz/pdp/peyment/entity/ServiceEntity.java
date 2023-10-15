package uz.pdp.peyment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.peyment.entity.enums.ServiceCategory;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceEntity extends BaseEntity{
    private String title;
    private ServiceCategory category;
    private Double commission;

}
