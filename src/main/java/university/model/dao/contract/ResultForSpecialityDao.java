package university.model.dao.contract;

import university.model.dao.entity.SpecialityRequestEntity;

import java.util.List;

public interface ResultForSpecialityDao extends CrudDao<SpecialityRequestEntity, Integer> {
    Integer countElementOfTableBySpecId(Integer idSpeciality);

    List<SpecialityRequestEntity> generateRating(Integer start, Integer recordsPerPage, Integer specialityId);
}
