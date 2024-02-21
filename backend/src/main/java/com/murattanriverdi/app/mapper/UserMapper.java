package com.murattanriverdi.app.mapper;

import com.murattanriverdi.app.dao.UserResponseDao;
import com.murattanriverdi.app.dto.UserRequestDto;
import com.murattanriverdi.app.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class );

    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    User requestDtoToUser(UserRequestDto userRequestDto);


    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    UserResponseDao userToResponseDao(User user);


}

