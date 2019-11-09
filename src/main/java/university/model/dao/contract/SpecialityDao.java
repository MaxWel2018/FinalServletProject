package university.model.dao.contract;

import university.model.dao.entity.CourseEntity;
import university.model.dao.entity.SpecialityEntity;

import java.util.List;

public interface SpecialityDao extends CrudDao<SpecialityEntity, Integer> {
    List<CourseEntity> getRequiredCoursesListBySpecId(Integer specialityId);

    void addCourse(Integer specialityId, Integer courseId);

}
