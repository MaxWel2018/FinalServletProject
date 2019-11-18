package university.model.service;

import university.domain.ExamResult;
import university.domain.SpecialityRequest;

import java.time.LocalDate;
import java.util.List;

public interface ResultService {

    void save(ExamResult examResult);

    void updateExamResult(ExamResult examResult);

    List<ExamResult> findAllByCourseIdAndData(Integer id, LocalDate date);

    List<ExamResult> findAll();

    ExamResult findExamResultByUserIdAndCourseId(Integer userId, Integer course);


    boolean updateGrade(Integer id, Integer grade);
}
