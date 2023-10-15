package uz.pdp.peyment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class Payment extends BaseEntity{
    @ManyToOne
    private UserEntity sender;
    @ManyToOne(fetch = FetchType.EAGER)
    private CardEntity senderCard;
    private Double amount;
    @ManyToOne
    private ServiceEntity service;
//    String a = """
//            { "phoneNumber": 123412, "company": "ucell"}""";
    private String fields;

}
