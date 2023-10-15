package uz.pdp.peyment.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.peyment.dto.request.CardCreateDto;

import uz.pdp.peyment.dto.request.UpdateCardDto;
import uz.pdp.peyment.dto.respons.CardResponse;
import uz.pdp.peyment.entity.CardEntity;
import uz.pdp.peyment.entity.UserEntity;
import uz.pdp.peyment.exception.DataNotFoundException;
import uz.pdp.peyment.repo.CardRepository;
import uz.pdp.peyment.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService
//        extends BaseService<
//        CardEntity,
//        UUID,
//        CardRepository,
//        CardResponse,
//        CardCreateDto
//        >
{

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

//    public CardService(CardRepository repository, ModelMapper modelMapper) {
//        super(repository, modelMapper);
//    }



    public CardResponse addCard(CardCreateDto cardCreateDto) {
        UserEntity userEntity = userRepository.findById(cardCreateDto.getOwnerId()).orElseThrow(
                        () -> new DataNotFoundException("user not found"));

        CardEntity cardEntity = modelMapper.map(cardCreateDto, CardEntity.class);
        cardEntity.setOwner(userEntity);
        cardRepository.save(cardEntity);
        CardResponse cardResponse = modelMapper.map(cardEntity, CardResponse.class);
        return cardResponse;
    }

    public List<CardEntity> getMyCards(UUID ownerId) {
        List<CardEntity> cardEntities = new ArrayList<>();
        List<CardEntity> cardEntityList = cardRepository.findCardEntitiesByOwnerId(ownerId)
                .orElseThrow( () -> new DataNotFoundException("user not found"));

        for (CardEntity cardEntity : cardEntityList) {
            if(cardEntity.isActive()) {
                cardEntities.add(cardEntity);
            }
        }
        return cardEntities;
    }

    public CardResponse findById(UUID id) {
        CardEntity cardEntity = cardRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("card not found"));
        if(cardEntity.isActive()) {
            return modelMapper.map(cardEntity, CardResponse.class);
        }
        throw  new DataNotFoundException("card not found");
    }

    public void updateCardBalance(UpdateCardDto updateCardDto) {
        CardEntity cardEntity = cardRepository.findCardEntitiesByCardNumber(updateCardDto.getCardNumber())
                .orElseThrow(() -> new DataNotFoundException("card not found"));
        cardEntity.setBalance(cardEntity.getBalance() + updateCardDto.getAmount());
        cardRepository.save(cardEntity);
    }

    public void deleteById(UUID id) {
        CardEntity cardEntity = cardRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("card not found"));
        cardEntity.setActive(false);
        cardRepository.save(cardEntity);
    }

    public void deleteByCardNumber(String cardNumber) {

        CardEntity cardEntity = cardRepository.findCardEntitiesByCardNumber(cardNumber)
                .orElseThrow(() -> new DataNotFoundException("card not found"));
        cardEntity.setActive(false);
        cardRepository.save(cardEntity);
    }
//    @Override
//    protected CardResponse mapEntityRoRES(CardEntity entity) {
//        return modelMapper.map(entity, CardResponse.class);
//    }
//
//    @Override
//    protected CardEntity mapCRToEntity(CardCreateDto createReq) {
//        return create(createReq);
//    }
}
