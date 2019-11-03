package university.controller.service.contract;

import university.model.dao.entity.Course;
import university.model.dao.entity.Speciality;

import java.util.List;

public interface SpecialityService extends Service<Speciality> {
    List<Course> getRequiredCoursesList(Integer specialityId);

    void addCourse(Integer specialityId, Integer courseId);
}
