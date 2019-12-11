package university.model.mapper;

import university.domain.Speciality;
import university.domain.User;
import university.model.dao.entity.SpecialityEntity;
import university.model.dao.entity.UserEntity;

import java.util.Optional;

public class UserMapper implements Mapper<User, UserEntity> {

    private SpecialityMapper specialityMapper;

    public UserMapper(SpecialityMapper specialityMapper) {
        this.specialityMapper = specialityMapper;
    }

    @Override
    public User mapEntityToDomain(UserEntity entity) {
        return User.newBuilder()
                .withId(entity.getId())
                .withEmail(entity.getEmail())
                .withFirstName(entity.getFirstName())
                .withSecondName(entity.getSecondName())
                .withPassword(entity.getPassword())
                .withRole(entity.getRole())
                .withSpeciality(mapSpecialityEntityToDomain(entity))
                .build();
    }

    @Override
    public UserEntity mapDomainToEntity(User user) {
        return UserEntity.newBuilder()
                .withId(user.getId())
                .withEmail(user.getEmail())
                .withFirstName(user.getFirstName())
                .withPassword(user.getPassword())
                .withSecondName(user.getSecondName())
                .withRole(user.getRole())
                .withSpecialityEntity(mapSpecialityToEntity(user))
                .build();
    }

    private Speciality mapSpecialityEntityToDomain(UserEntity entity) {
        return Optional.ofNullable(entity)
                .map(UserEntity::getSpecialityEntity)
                .map(x -> specialityMapper.mapEntityToDomain(x))
                .orElse(null);
    }

    private SpecialityEntity mapSpecialityToEntity(User user) {
        return Optional.ofNullable(user)
                .map(User::getSpeciality)
                .map(x -> specialityMapper.mapDomainToEntity(x))
                .orElse(null);
    }


}
