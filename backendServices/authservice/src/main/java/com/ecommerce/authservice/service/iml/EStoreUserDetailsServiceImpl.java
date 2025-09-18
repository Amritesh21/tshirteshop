package com.ecommerce.authservice.service.iml;

import com.ecommerce.authservice.constants.ExceptionMessages;
import com.ecommerce.authservice.dto.UserDetailsDTO;
import com.ecommerce.authservice.entity.Authority;
import com.ecommerce.authservice.entity.UserEntity;
import com.ecommerce.authservice.utils.mapper.iml.MapUserDTOToEntity;
import com.ecommerce.authservice.utils.mapper.iml.MapUserEntityToDTO;
import com.ecommerce.authservice.repository.EStoreUserAuthorityRepo;
import com.ecommerce.authservice.repository.EStoreUserDetailsRepo;
import com.ecommerce.authservice.service.EStoreUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EStoreUserDetailsServiceImpl implements EStoreUserDetailsService {

    private MapUserDTOToEntity mapUserDTOToEntity;
    private EStoreUserDetailsRepo eStoreUserDetailsRepo;
    private EStoreUserAuthorityRepo eStoreUserAuthorityRepo;
    private MapUserEntityToDTO mapUserEntityToDTO;

    EStoreUserDetailsServiceImpl(MapUserDTOToEntity mapUserDTOToEntity,
                                 EStoreUserDetailsRepo eStoreUserDetailsRepo,
                                 EStoreUserAuthorityRepo eStoreUserAuthorityRepo,
                                 MapUserEntityToDTO mapUserEntityToDTO
    ) {
        this.mapUserDTOToEntity = mapUserDTOToEntity;
        this.eStoreUserDetailsRepo = eStoreUserDetailsRepo;
        this.eStoreUserAuthorityRepo = eStoreUserAuthorityRepo;
        this.mapUserEntityToDTO = mapUserEntityToDTO;
    }

    private Optional<UserEntity> getUserEntityObj(String username) {
        return eStoreUserDetailsRepo.findById(username);
    }

    @Override
    @Transactional
    public UserDetailsDTO getUserDetailsByUsername(String username) {
        Optional<UserEntity> userEntityOptional = getUserEntityObj(username);
        if (userEntityOptional.isEmpty()) {
            throw new RuntimeException(ExceptionMessages.INVALID_USERNAME.getMessage());
        }
        UserEntity userEntity = userEntityOptional.get();
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        mapUserEntityToDTO.mapUserEntityAndDTO(userEntity, userDetailsDTO);
        return userDetailsDTO;
    }

    private void addAuthorityToUser(UserEntity userEntity, UserDetailsDTO userDetailsDTO) {
        userDetailsDTO.getAuthorities().forEach(x -> {
          Optional<Authority> authority = eStoreUserAuthorityRepo.findById(x);
          if (authority.isPresent()) {
              userEntity.getAuthorities().add(authority.get());
          } else {
              throw new RuntimeException(ExceptionMessages.INVALID_AUTHORITY_ASSOCIATED.getMessage());
          }
        });
    }

    @Override
    @Transactional
    public boolean createNewUser(UserDetailsDTO userDetailsDTO) {

        Optional<UserEntity> userEntityOptional = getUserEntityObj(userDetailsDTO.getEmailId());
        if (userEntityOptional.isPresent()) {
            throw new RuntimeException(ExceptionMessages.USERNAME_ALREADY_PRESENT.getMessage());
        }
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername(userDetailsDTO.getEmailId());
        mapUserDTOToEntity.mapUserEntityAndDTO(newUserEntity, userDetailsDTO);
        addAuthorityToUser(newUserEntity, userDetailsDTO);

        eStoreUserDetailsRepo.save(newUserEntity);
        return true;
    }

    @Override
    @Transactional
    public boolean updateUser(UserDetailsDTO userDetailsDTO) {
        Optional<UserEntity> optionalUserEntity = getUserEntityObj(userDetailsDTO.getEmailId());
        if (optionalUserEntity.isEmpty()) {
            throw new RuntimeException(ExceptionMessages.INVALID_USERNAME.getMessage());
        }
        UserEntity userEntity = optionalUserEntity.get();
        mapUserDTOToEntity.mapUserEntityAndDTO(userEntity, userDetailsDTO);
        addAuthorityToUser(userEntity, userDetailsDTO);

        eStoreUserDetailsRepo.save(userEntity);
        return true;
    }





}
