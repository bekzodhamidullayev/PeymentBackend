package uz.pdp.peyment.service.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uz.pdp.peyment.entity.UserEntity;
import uz.pdp.peyment.repo.UserRepository;

@Component
@Slf4j
public class UserEntityValidator extends BaseValidation<UserEntity, UserRepository> {
    @Override
    public void validate(UserEntity entity) {
        log.info("validating " + entity.getClass().getName());
        checkEmailExists(entity.getUsername());
        log.info("validated " + entity.getClass().getName());
    }

    private void checkEmailExists(String username) {
//        if(repository.findUserEntitiesByUsername)
    }
}
