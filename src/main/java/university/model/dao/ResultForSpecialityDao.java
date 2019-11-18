package university.model.dao;

import university.domain.SpecialityRequest;
import university.model.dao.entity.SpecialityRequestEntity;

import java.util.List;

public interface ResultForSpecialityDao extends CrudDao<SpecialityRequestEntity, Integer> {
    Integer countElementOfTableBySpecId(Integer idSpeciality);

    List<SpecialityRequestEntity> generateRating(Integer start, Integer recordsPerPage, Integer specialityId);

    void setResultForSpeciality(SpecialityRequestEntity specialityRequestEntity);

    SpecialityRequestEntity findByUserId(Integer userId);

    List<SpecialityRequestEntity> findToEnrollmentBySpecId(Integer specId,Integer governmentOrder);

    void updateConfirmedByUserId(Integer id, Boolean confirmed);

}
