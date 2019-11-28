package university.model.service;

import university.domain.UserResult;

import java.util.List;

public interface ResultForSpecService {

    List<UserResult> generateRating(Page page, Integer specialityId);

    void setResultForSpeciality(UserResult userResult);

    UserResult findByUserId(Integer userId);

    List<UserResult> findToEnrollmentBySpecId(Integer specId, Integer governmentOrder);

    void updateConfirmedByUserId(Integer id, Boolean confirmed);



}
