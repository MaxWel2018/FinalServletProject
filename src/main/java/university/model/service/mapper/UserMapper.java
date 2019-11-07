package university.model.service.mapper;

import university.domain.User;
import university.model.dao.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public User mapUserEntityToUser(UserEntity entity) {
        return User.newBuilder()
                .withId(entity.getId())
                .withEmail(entity.getEmail())
                .withFirstName(entity.getFirstName())
                .withSecondName(entity.getSecondName())
                .withPassword(entity.getPassword())
                .withRole(entity.getRole())
                .build();
    }

    public UserEntity mapUserToUserEntity(User user) {
        return UserEntity.newBuilder()
                .withEmail(user.getEmail())
                .withFirstName(user.getFirstName())
                .withPassword(user.getPassword())
                .withSecondName(user.getSecondName())
                .withRole(user.getRole())
                .build();
    }

    public List<User> mapUserEntityListToUserList(List<UserEntity> entities) {
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : entities) {
            users.add(mapUserEntityToUser(userEntity));
        }

        return users;

    }
}
