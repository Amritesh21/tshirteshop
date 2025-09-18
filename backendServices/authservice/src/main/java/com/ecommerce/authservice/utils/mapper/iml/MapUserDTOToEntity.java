package com.ecommerce.authservice.utils.mapper.iml;

import com.ecommerce.authservice.dto.UserDetailsDTO;
import com.ecommerce.authservice.entity.CompleteUserEntity;
import com.ecommerce.authservice.entity.UserEntity;
import com.ecommerce.authservice.utils.mapper.MapUserEntityAndDTO;
import org.springframework.stereotype.Component;

@Component
public class MapUserDTOToEntity implements MapUserEntityAndDTO {
    @Override
    public void mapUserEntityAndDTO(UserEntity userEntity, UserDetailsDTO userDetailsDTO) {
        userEntity.setPassword(userDetailsDTO.getPassword());
        userEntity.setFirstName(userDetailsDTO.getFirstName());
        userEntity.setLastName(userDetailsDTO.getLastName());

        CompleteUserEntity completeUserEntity = new CompleteUserEntity();
        completeUserEntity.setAddress(userDetailsDTO.getAddress());
        completeUserEntity.setPhoneNo(userDetailsDTO.getPhoneNo());
        completeUserEntity.setAlternatePhoneNo(userDetailsDTO.getAlternateNo());
        completeUserEntity.setUserEntity(userEntity);

        userEntity.setCompleteUserEntity(completeUserEntity);

    }
}
