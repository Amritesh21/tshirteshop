package com.ecommerce.authservice.utils.mapper;

import com.ecommerce.authservice.dto.UserDetailsDTO;
import com.ecommerce.authservice.entity.UserEntity;

public interface MapUserEntityAndDTO {

    public void mapUserEntityAndDTO(UserEntity userEntity, UserDetailsDTO userDetailsDTO);

}
