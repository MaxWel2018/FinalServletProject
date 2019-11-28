package university.model.mapper;

import university.domain.Course;
import university.domain.ExamResult;
import university.domain.User;
import university.model.dao.entity.ExamResultEntity;

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
                .withUser(mapUserEntityToDomain(examResultEntity))
                .build();
    }

    private User mapUserEntityToDomain(ExamResultEntity examResultEntity) {
        return Optional.ofNullable(userMapper.mapEntityToDomain(examResultEntity.getUserEntity())).orElse(User.newBuilder().build());
    }

    @Override
    public ExamResultEntity mapDomainToEntity(ExamResult examResult) {
        return ExamResultEntity.newBuilder()
                .withId(examResult.getId())
                .withCourseId(examResult.getCourse().getId())
                .withDate(examResult.getDate())
                .withMark(examResult.getMark())
                .withUserId(examResult.getUserId())
                .withUserEntity(userMapper.mapDomainToEntity(examResult.getUser()))
                .withCourseEntity(courseMapper.mapDomainToEntity(examResult.getCourse()))
                .build();
    }

    private Course mapCourseEntityToDomain(ExamResultEntity examResultEntity) {
        return Optional.ofNullable(courseMapper.mapEntityToDomain(examResultEntity.getCourseEntity())).orElse(new Course());
    }

}
