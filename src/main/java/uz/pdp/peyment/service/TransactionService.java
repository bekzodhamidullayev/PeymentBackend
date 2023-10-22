package uz.pdp.peyment.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import uz.pdp.peyment.dto.request.TransactionCreateDto;
import uz.pdp.peyment.dto.request.TransactionInPeriod;
import uz.pdp.peyment.dto.respons.TransactionResponseDto;
import uz.pdp.peyment.entity.CardEntity;
import uz.pdp.peyment.entity.TransactionEntity;
import uz.pdp.peyment.exception.DataNotFoundException;

import uz.pdp.peyment.exception.NotEnoughFundsException;
import uz.pdp.peyment.repo.CardRepository;

import uz.pdp.peyment.repo.TransactionRepository;
import uz.pdp.peyment.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TransactionService {
    private final ModelMapper modelMapper;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public void addTr(TransactionCreateDto transactionDto) {
        CardEntity senderCard = cardRepository.findCardEntitiesByCardNumber(transactionDto.getSenderCardNum())
                .orElseThrow(() -> new DataNotFoundException("sender card not found"));
        CardEntity receiverCard = cardRepository.findCardEntitiesByCardNumber(transactionDto.getReceiverCardNum())
                .orElseThrow(() -> new DataNotFoundException("receiver card not found"));

        if(senderCard.getBalance() <= transactionDto.getAmount() * 1.01) {
            throw  new NotEnoughFundsException("Insufficient funds in the account");
        } else if (!(senderCard.isActive() || receiverCard.isActive())){
            throw new DataNotFoundException("CardEntity is not active");
        }
        senderCard.setBalance(senderCard.getBalance() - transactionDto.getAmount() * 1.01);
        receiverCard.setBalance(receiverCard.getBalance() + transactionDto.getAmount());
        cardRepository.save(senderCard);
        cardRepository.save(receiverCard);

        TransactionEntity transaction = TransactionEntity.builder()
                .ownerId(senderCard.getOwner().getId())
                .sender(senderCard)
                .receiver(receiverCard)
                .amount(transactionDto.getAmount())
                .build();
        transactionRepository.save(transaction);
    }


    public List<TransactionEntity> getAllInPeriod(TransactionInPeriod transaction) {

        return transactionRepository.findTransactionEntitiesByOwnerIdAndCreateDateBetween(transaction.getOwnerId(), transaction.getFirstTime(), transaction.getLastTime());
    }

    public List<TransactionResponseDto> getAll(UUID ownerId) {
        List<TransactionResponseDto> responseDtoList = new ArrayList<>();
        List<TransactionEntity> transactionEntitiesByOwnerId = transactionRepository.findTransactionEntitiesByOwnerId(ownerId);

        for (TransactionEntity entity : transactionEntitiesByOwnerId) {
            TransactionResponseDto dto = TransactionResponseDto.builder()
                    .senderCardNum(entity.getSender().getCardNumber())
                    .receiverCardNum(entity.getReceiver().getCardNumber())
                    .balance(entity.getAmount())
                    .build();
            responseDtoList.add(dto);
        }
        return responseDtoList;
    }

}
