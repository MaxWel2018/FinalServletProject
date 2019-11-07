package university.model.dao.contract;

import university.model.dao.entity.UserEntity;

import java.util.Optional;

public interface UserDao extends CrudDao<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);

}
