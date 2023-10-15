package uz.pdp.peyment.repo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.peyment.dto.respons.TransactionResponseDto;
import uz.pdp.peyment.entity.CardEntity;
import uz.pdp.peyment.entity.TransactionEntity;
import uz.pdp.peyment.entity.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
//    List<TransactionEntity> findTransactionEfindAllBySenderOrReceiverntitiesBySenderOrReceiver(UserEntity sender, UserEntity receiver);
//    List<TransactionEntity> findAllBySenderOrReceiver(UserEntity sender, UserEntity receiver);

//    List<TransactionEntity> findTransactionEntitiesByOwnerIdAndCreateDateBetween(UUID ownerId, LocalDateTime startDate, LocalDateTime endDate);
List<TransactionEntity> findAllBySenderOrReceiver(CardEntity sender, CardEntity receiver);
@Query(value = """
                   select t from transaction t
                   where (t.sender.id =:cardId or t.receiver.id =:cardId)
                   and t.createDate <= :firstDate
                   and t.createDate >=:lastDate
                   order by t.createDate
                   """)
List<TransactionEntity> getAllInPeriod(UUID cardId, LocalDate firstDate, LocalDate lastDate, Pageable pageable);
    List<TransactionEntity> findTransactionEntitiesByOwnerIdAndCreateDateBetween(UUID ownerId, LocalDateTime firstDate, LocalDateTime lastDate);
    List<TransactionEntity> findTransactionEntitiesByOwnerId(UUID ownerId);
}
