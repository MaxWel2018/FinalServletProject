package university.model.mapper;

import university.domain.ExamResult;
import university.model.dao.entity.ExamResultEntity;
import university.model.service.CourseService;
import university.model.service.UserService;

import java.util.Optional;

public class ExamResultMapper implements Mapper<ExamResult, ExamResultEntity> {
    private final UserService userService;
    private final CourseService courseService;

    public ExamResultMapper(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }


    @Override
    public ExamResult mapEntityToDomain(ExamResultEntity examResultEntity) {
        return ExamResult.newBuilder()
                .withId(examResultEntity.getId())
                .withCourseId(examResultEntity.getCourseId())
                .withDate(examResultEntity.getDate())
                .withMark(examResultEntity.getMark())
                .withUserId(examResultEntity.getUserId())
                .withCourse(courseService.findById(examResultEntity.getCourseId()))
                .withUser(userService.findById(examResultEntity.getUserId()))
                .build();
    }

    @Override
    public ExamResultEntity mapDomainToEntity(ExamResult examResult) {
        return ExamResultEntity.newBuilder()
                .withId(examResult.getId())
                .withCourseId(examResult.getCourse().getId())
                .withDate(examResult.getDate())
                .withMark(examResult.getMark())
                .withUserId(examResult.getUserId())
                .build();
    }



}
