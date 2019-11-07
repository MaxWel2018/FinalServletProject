package university.model.service.contract;

import university.model.dao.entity.CourseEntity;
import university.model.dao.entity.SpecialityEntity;

import java.util.List;
import java.util.Optional;

public interface SpecialityService   {
    void register(SpecialityEntity specialityEntity);

    Optional<SpecialityEntity> findById(Integer id);

    void update(SpecialityEntity entity);

    List<SpecialityEntity> findAll();

    List<CourseEntity> getRequiredCoursesList(Integer specialityId);

    void addCourse(Integer specialityId, Integer courseId);
}
