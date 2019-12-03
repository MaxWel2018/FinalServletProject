package university.model.mapper;

import university.domain.Course;
import university.domain.ExamResult;
import university.domain.User;
import university.model.dao.entity.CourseEntity;
import university.model.dao.entity.ExamResultEntity;
import university.model.dao.entity.UserEntity;

import java.util.Optional;

public class ExamResultMapper implements Mapper<ExamResult, ExamResultEntity> {

    private final CourseMapper courseMapper;

    private final UserMapper userMapper;

    public ExamResultMapper(CourseMapper courseMapper, UserMapper userMapper) {
        this.courseMapper = courseMapper;
        this.userMapper = userMapper;
    }

    @Override
    public ExamResult mapEntityToDomain(ExamResultEntity examResultEntity) {
        return ExamResult.newBuilder()
                .withId(examResultEntity.getId())
                .withCourseId(examResultEntity.getCourseId())
                .withDate(examResultEntity.getDate())
                .withMark(examResultEntity.getMark())
                .withUserId(examResultEntity.getUserId())
                .withCourse(mapCourseEntityToDomain(examResultEntity))
                .withUser(mapExamResultEntityToUser(examResultEntity))
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
                .withUserEntity(mapExamResultToUserEntity(examResult))
                .withCourseEntity(mapExamResultToCourseEntity(examResult))
                .build();
    }

    private Course mapCourseEntityToDomain(ExamResultEntity examResultEntity) {
        return Optional.ofNullable(courseMapper.mapEntityToDomain(examResultEntity.getCourseEntity())).orElse(new Course());
    }

    private User mapExamResultEntityToUser(ExamResultEntity examResultEntity) {
        return Optional.ofNullable(userMapper.mapEntityToDomain(examResultEntity.getUserEntity())).orElse(User.newBuilder().build());
    }

    private CourseEntity mapExamResultToCourseEntity(ExamResult examResult) {
        return Optional.ofNullable(courseMapper.mapDomainToEntity(examResult.getCourse())).orElse(new CourseEntity());
    }

    private UserEntity mapExamResultToUserEntity(ExamResult examResult) {
        return Optional.ofNullable(userMapper.mapDomainToEntity(examResult.getUser())).orElse(UserEntity.newBuilder().build());
    }



}
