package com.superapp.auth_service.user;

import com.superapp.auth_service.user.role.ERole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);
    @Mapping(target = "role", expression = "java(this.roleToString(user.getRole()))")
    UserDto userToUserDto(User user);
    @Mapping(target = "role", expression = "java(this.stringToRole(userDto.getRole()))")
    User userDtoToUser(UserDto userDto);
    List<UserDto> userListToUserDtoList(List<User> userList);
    List<User> userDtoListToUserList(List<UserDto> userDtoList);


    default String roleToString(ERole role) {
        if (role == null)
            return null;
        else
            return role.name();
    }

    default ERole stringToRole(String role) {
        if (role == null)
            return null;
        else
            return ERole.valueOf(role);
    }


}
