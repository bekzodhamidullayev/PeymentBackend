package uz.pdp.peyment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.peyment.dto.request.TransactionCreateDto;
import uz.pdp.peyment.dto.request.TransactionInPeriod;
import uz.pdp.peyment.dto.respons.TransactionResponseDto;
import uz.pdp.peyment.entity.TransactionEntity;
import uz.pdp.peyment.service.TransactionService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    @PostMapping("/add-transaction")
    public void addTr(
            @RequestBody TransactionCreateDto transactionDto
    ) {
       transactionService.addTr(transactionDto);
    }

    @PostMapping("/in-period")
    public List<TransactionEntity> inPeriod(
            @RequestBody TransactionInPeriod transaction
    ) {
        System.out.println(transaction);
        return transactionService.getAllInPeriod(transaction);
    }

    @GetMapping("/history/{ownerId}")
    public List<TransactionResponseDto> getAll(@PathVariable UUID ownerId) {
        return transactionService.getAll(ownerId);
    }
//    @GetMapping("/my-transactions/{userId}")
//    public List<TransactionEntity> myTransactions(
//            @PathVariable UUID userId
//    ) {
//        return transactionService.getAll(userId);
//    }

//    @GetMapping("/transaction-history-in-period")
//    public List<TransactionEntity> transactionInPeriod (
//            @RequestBody TransactionHistoryInPeriodDto historyInPeriodDto
//            ) {
//        return transactionService.findByCardNumberAndTransactionDateBetween(historyInPeriodDto);
//    }


}
