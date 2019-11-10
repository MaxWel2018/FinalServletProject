package university.model.mapper;

import university.domain.User;
import university.model.dao.entity.UserEntity;

public class UserMapper implements Mapper<User,UserEntity> {
    @Override
    public User mapEntityToDomain(UserEntity entity) {
        return User.newBuilder()
                .withId(entity.getId())
                .withEmail(entity.getEmail())
                .withFirstName(entity.getFirstName())
                .withSecondName(entity.getSecondName())
                .withPassword(entity.getPassword())
                .withRole(entity.getRole())
                .build();
    }

    @Override
    public UserEntity mapDomainToEntity(User user) {
        return UserEntity.newBuilder()
                .withEmail(user.getEmail())
                .withFirstName(user.getFirstName())
                .withPassword(user.getPassword())
                .withSecondName(user.getSecondName())
                .withRole(user.getRole())
                .build();
    }
}
