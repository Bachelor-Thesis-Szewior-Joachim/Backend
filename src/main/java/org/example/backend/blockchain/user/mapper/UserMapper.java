package org.example.backend.blockchain.user.mapper;

import org.example.backend.blockchain.user.entity.User;
import org.example.backend.blockchain.user.entity.UserDto;

public class UserMapper {

    public static User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .seedPhrase(userDto.getSeedPhrase())
                .accounts(userDto.getAccounts())
                .build();
    }

    public static UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .seedPhrase(user.getSeedPhrase())
                .accounts(user.getAccounts())
                .build();
    }
}
