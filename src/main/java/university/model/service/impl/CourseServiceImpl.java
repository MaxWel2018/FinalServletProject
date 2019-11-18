package university.model.service.impl;

import university.domain.Course;
import university.model.dao.CourseDao;
import university.model.dao.exception.EntityNotFoundException;
import university.model.mapper.CourseMapper;
import university.model.service.CourseService;

import java.util.List;
import java.util.stream.Collectors;

public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseDao courseDao, CourseMapper courseMapper) {
        this.courseDao = courseDao;
        this.courseMapper = courseMapper;
    }

    @Override
    public Course findById(Integer id) {
        return courseMapper.mapEntityToDomain(courseDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found ")));
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll().stream().map(courseMapper::mapEntityToDomain).collect(Collectors.toList());
    }

}
