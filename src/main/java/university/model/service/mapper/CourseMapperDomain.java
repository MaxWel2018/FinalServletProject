package university.model.service.mapper;

import university.domain.Course;
import university.model.dao.entity.CourseEntity;

public class CourseMapperDomain {

    public Course mapCourseEntityToCourse(CourseEntity courseEntity) {
        return new Course(courseEntity.getId(), courseEntity.getName());
    }

}
