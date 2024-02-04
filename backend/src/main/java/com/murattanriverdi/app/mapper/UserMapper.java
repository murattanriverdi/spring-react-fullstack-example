package com.murattanriverdi.app.mapper;

import com.murattanriverdi.app.dto.request.UserRequestDto;
import com.murattanriverdi.app.dto.response.UserResponseDto;
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
   UserResponseDto userToResponseDto(User user);


}

