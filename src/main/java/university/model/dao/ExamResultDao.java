package university.model.dao;

import university.domain.ExamResult;
import university.model.dao.entity.ExamResultEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExamResultDao extends CrudDao<ExamResultEntity, Integer> {
    Optional<ExamResultEntity> findByUserIdAndCourseId(Integer userId, Integer courseId);

     List<ExamResultEntity> findAllByCourseIdAndData(Integer id, LocalDate date);

    void updateGrade(Integer id,Integer grade);



}
