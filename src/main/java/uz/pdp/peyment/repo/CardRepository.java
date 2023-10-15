package uz.pdp.peyment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.peyment.entity.CardEntity;
import uz.pdp.peyment.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {
    Optional<List<CardEntity>> findCardEntitiesByOwnerId(UUID ownerId);
    Optional<CardEntity>findCardEntitiesByCardNumber(String cardNumber);
}
