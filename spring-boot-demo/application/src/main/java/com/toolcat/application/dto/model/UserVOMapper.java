package com.toolcat.application.dto.model;

import com.toolcat.application.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserVOMapper {

    UserVOMapper INSTANCE = Mappers.getMapper(UserVOMapper.class);

    //    @Mapping(source = "address", target = "address", ignore = true)
//    @Mapping(source = "phone", target = "phone", ignore = true)
//    @Mapping(source = "email", target = "email", ignore = true)
//    @Mapping(source = "password", target = "password", ignore = true)
    @Mappings({
            @Mapping(source = "address", target = "address", ignore = true),
            @Mapping(source = "phone", target = "phone", ignore = true),
            @Mapping(source = "email", target = "email", ignore = true),
            @Mapping(source = "password", target = "password", ignore = true)
    })
    UserVO toVO(User user);

    List<UserVO> toVO(List<User> userList);

    UserVO toDO(UserVO userVO);

}
