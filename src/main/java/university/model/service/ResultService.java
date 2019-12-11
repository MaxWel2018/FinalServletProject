package university.model.service;

import university.domain.ExamResult;

import java.time.LocalDate;
import java.util.List;

public interface ResultService {

    void save(ExamResult examResult);

    List<ExamResult> findAllByCourseIdAndData(Integer id, LocalDate date);

    List<ExamResult> findAll();

    ExamResult findExamResultByUserIdAndCourseId(Integer userId, Integer course);

    boolean updataGrade(Integer id, Integer grade);
}
