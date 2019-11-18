package university.model.service;

import university.domain.SpecialityRequest;
import university.model.dao.entity.SpecialityRequestEntity;

import java.util.List;

public interface ResultForSpecService {

    List<SpecialityRequest> generateRating(Page page, Integer specialityId);

    void setResultForSpeciality(SpecialityRequest specialityRequest);

    SpecialityRequest findByUserId(Integer userId);

    List<SpecialityRequest> findToEnrollmentBySpecId(Integer specId, Integer governmentOrder);

    void updateConfirmedByUserId(Integer id, Boolean confirmed);



}
