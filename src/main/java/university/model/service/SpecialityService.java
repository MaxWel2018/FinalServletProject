package university.model.service;

import university.domain.Course;
import university.domain.Speciality;
import university.model.dao.entity.SpecialityEntity;

import java.util.List;

public interface SpecialityService   {
    void register(SpecialityEntity specialityEntity);

    Speciality findById(Integer id);

    void update(Speciality entity);

    List<Speciality> findAll();

    List<Course> getRequiredCoursesListBySpecId(Integer specialityId);

    Integer countElementOfTableBySpecId(Integer idSpeciality);

    void addCourse(Integer specialityId, Integer courseId);
}
