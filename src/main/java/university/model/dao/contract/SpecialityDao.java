package university.model.dao.contract;

import university.model.dao.entity.Course;
import university.model.dao.entity.Speciality;

import java.util.List;

public interface SpecialityDao extends CrudDao<Speciality, Integer> {
    List<Course> getRequiredCoursesList(Integer specialityId);

    void addCourse(Integer specialityId, Integer courseId);

}
