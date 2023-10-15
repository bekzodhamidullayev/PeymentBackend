package uz.pdp.peyment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.peyment.dto.request.CardCreateDto;
import uz.pdp.peyment.dto.request.UpdateCardDto;
import uz.pdp.peyment.dto.respons.CardResponse;
import uz.pdp.peyment.entity.CardEntity;
import uz.pdp.peyment.service.CardService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    @PostMapping("/add-card")
    public CardResponse addCard(
            @Valid
            @RequestBody CardCreateDto cardDto
    ) {
        return cardService.addCard(cardDto);
    }

    @GetMapping("/my-cards/{ownerId}")
    public List<CardEntity> myCards(
            @PathVariable UUID ownerId
    ) {
        return cardService.getMyCards(ownerId);
    }
    @GetMapping("/find/{id}")
    public CardResponse findById(@PathVariable UUID id) {
        return cardService.findById(id);
    }

    @PutMapping("/update-card-balance")
    public void updateCard(
            @RequestBody UpdateCardDto updateCardDto
            ) {
        cardService.updateCardBalance(updateCardDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable UUID id) {
        System.out.println();
        cardService.deleteById(id);
    }
    @DeleteMapping("/delete-by-card-number")
    public void deleteCardByNumber(@RequestParam String cardNumber) {
        cardService.deleteByCardNumber(cardNumber);
    }
}
