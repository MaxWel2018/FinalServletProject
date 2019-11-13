package university.model.service.impl;

import university.domain.Course;
import university.model.dao.CourseDao;
import university.model.dao.exception.EntityNotFoundException;
import university.model.mapper.CourseMapper;
import university.model.service.CourseService;

public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseDao courseDao, CourseMapper courseMapper) {
        this.courseDao = courseDao;
        this.courseMapper = courseMapper;
    }

    @Override
    public Course findById(Integer id) {
        return courseMapper.mapCourseEntityToCourse(courseDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found ")));
    }

}
