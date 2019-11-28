package university.model.dao;

import university.model.dao.entity.UserResultEntity;

import java.util.List;

public interface ResultForSpecialityDao extends CrudDao<UserResultEntity, Integer> {
    Integer countElementOfTableBySpecId(Integer idSpeciality);

    List<UserResultEntity> generateRating(Integer start, Integer recordsPerPage, Integer specialityId);

    void setResultForSpeciality(UserResultEntity userResultEntity);

    UserResultEntity findByUserId(Integer userId);

    List<UserResultEntity> findToEnrollmentBySpecId(Integer specId, Integer governmentOrder);

    void updateConfirmedByUserId(Integer id, Boolean confirmed);

}
