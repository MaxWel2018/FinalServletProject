package university.model.dao.entity;

import java.time.LocalDate;
import java.util.Objects;

public class ExamResultEntity {

    private final Integer id;
    private final LocalDate date;
    private final Integer mark;
    private final Integer courseId;
    private final Integer userId;
    private final UserEntity userEntity;
    private final CourseEntity courseEntity;

    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getMark() {
        return mark;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public CourseEntity getCourseEntity() {
        return courseEntity;
    }

    private ExamResultEntity(Builder builder) {
        id = builder.id;
        date = builder.date;
        mark = builder.mark;
        courseId = builder.courseId;
        userId = builder.userId;
        userEntity = builder.userEntity;
        courseEntity = builder.courseEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExamResultEntity)) {
            return false;
        }
        ExamResultEntity that = (ExamResultEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(mark, that.mark) &&
                Objects.equals(courseId, that.courseId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userEntity, that.userEntity) &&
                Objects.equals(courseEntity, that.courseEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, mark, courseId, userId, userEntity, courseEntity);
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private LocalDate date;
        private Integer mark;
        private Integer courseId;
        private Integer userId;
        private UserEntity userEntity;
        private CourseEntity courseEntity;

        private Builder() {
        }

        public Builder withId(Integer val) {
            id = val;
            return this;
        }

        public Builder withDate(LocalDate val) {
            date = val;
            return this;
        }

        public Builder withMark(Integer val) {
            mark = val;
            return this;
        }

        public Builder withCourseId(Integer val) {
            courseId = val;
            return this;
        }

        public Builder withUserId(Integer val) {
            userId = val;
            return this;
        }

        public Builder withUserEntity(UserEntity val) {
            userEntity = val;
            return this;
        }

        public Builder withCourseEntity(CourseEntity val) {
            courseEntity = val;
            return this;
        }

        public ExamResultEntity build() {
            return new ExamResultEntity(this);
        }
    }
}
