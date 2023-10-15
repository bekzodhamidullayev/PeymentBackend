package uz.pdp.peyment.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.peyment.dto.request.UserCreateDto;
import uz.pdp.peyment.dto.request.Login;
import uz.pdp.peyment.dto.respons.UserResponseDTO;
import uz.pdp.peyment.entity.UserEntity;
import uz.pdp.peyment.exception.DataNotFoundException;
import uz.pdp.peyment.exception.WrongPasswordException;
import uz.pdp.peyment.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService
//        extends BaseService<UserEntity, UUID, UserRepository, UserEntity, UserCreateDto>
{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

//    public UserService(UserRepository repository, UserRepository userRepository, ModelMapper modeMapper) {
//        super(repository, modeMapper);
//        this.userRepository = userRepository;
//    }

    public UserResponseDTO save (UserCreateDto userDto) {
        UserEntity user = modelMapper.map(userDto, UserEntity.class);
        userRepository.save(user);
        return  modelMapper.map(user, UserResponseDTO.class);
    }

    public UserResponseDTO signIn(Login login) {
        UserEntity userEntity = userRepository.findUserEntitiesByUsername(login.getUsername())
                .orElseThrow(() -> new DataNotFoundException("user not found"));
        if(userEntity.getPassword().equals(login.getPassword())) {
            return modelMapper.map(userEntity, UserResponseDTO.class);
        }
        throw new WrongPasswordException("password didn't match");
    }

//    @Override
//    protected UserEntity mapEntityRoRES(UserEntity entity) {
//        return null;
//    }
//
//    @Override
//    protected UserEntity mapCRToEntity(UserCreateDto createReq) {
//        return null;
//    }
}
