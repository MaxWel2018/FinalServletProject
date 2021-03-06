package university.model.mapper;

import university.domain.Course;
import university.model.dao.entity.CourseEntity;

public class CourseMapper implements Mapper<Course, CourseEntity> {
    @Override
    public Course mapEntityToDomain(CourseEntity courseEntity) {
        return new Course(courseEntity.getId(), courseEntity.getName());
    }

    @Override
    public CourseEntity mapDomainToEntity(Course course) {
        return new CourseEntity(course.getId(), course.getName());
    }

}
