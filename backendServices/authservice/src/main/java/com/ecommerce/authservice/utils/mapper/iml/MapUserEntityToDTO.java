package com.ecommerce.authservice.utils.mapper.iml;

import com.ecommerce.authservice.dto.UserDetailsDTO;
import com.ecommerce.authservice.entity.Authority;
import com.ecommerce.authservice.entity.CompleteUserEntity;
import com.ecommerce.authservice.entity.UserEntity;
import com.ecommerce.authservice.utils.mapper.MapUserEntityAndDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapUserEntityToDTO implements MapUserEntityAndDTO {
    @Override
    public void mapUserEntityAndDTO(UserEntity userEntity, UserDetailsDTO userDetailsDTO) {
        userDetailsDTO.setEmailId(userEntity.getUsername());
        userDetailsDTO.setPassword(userEntity.getPassword());
        userDetailsDTO.setFirstName(userEntity.getFirstName());
        userDetailsDTO.setLastName(userEntity.getLastName());

        CompleteUserEntity completeUserEntity = userEntity.getCompleteUserEntity();

        userDetailsDTO.setAddress(completeUserEntity.getAddress());
        userDetailsDTO.setPhoneNo(completeUserEntity.getPhoneNo());
        userDetailsDTO.setAlternateNo(completeUserEntity.getAlternatePhoneNo());

        List<String> userAuthorities = userEntity
                .getAuthorities()
                .stream()
                .map(Authority::getAuthority).toList();
        userDetailsDTO.setAuthorities(userAuthorities);

    }
}
