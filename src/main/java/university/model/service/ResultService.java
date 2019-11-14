package university.model.service;

import university.domain.ExamResult;
import university.domain.SpecialityRequest;

import java.util.List;

public interface ResultService {

    void save(ExamResult examResult);

    void updateExamResult(ExamResult examResult);

    List<SpecialityRequest> generateRating(Page page, Integer specialityId);

}
