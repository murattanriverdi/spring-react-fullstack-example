package com.murattanriverdi.app.mapper;

import com.murattanriverdi.app.dao.UserListDao;
import com.murattanriverdi.app.dto.CreateUserRequestDto;
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
    User requestDtoToUser(CreateUserRequestDto createUserRequestDto);


    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    CreateUserRequestDto userToResponseDao(User user);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "image", source = "image")
    UserListDao userToUserListDao(User user);


}
