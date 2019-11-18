package university.model.service;

import university.domain.Course;

import java.util.List;

public interface CourseService {
    Course findById(Integer id);

    List<Course> findAll();


}
