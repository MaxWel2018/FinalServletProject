package university.model.dao.entity;

import java.time.LocalDate;
import java.util.Objects;

public class ExamResultEntity {

    private Integer id;
    private LocalDate date;
    private Integer mark;
    private Integer courseId;
    private Integer userId;
    private UserEntity userEntity;
    private CourseEntity courseEntity;

    private ExamResultEntity(Builder builder) {
        id = builder.id;
        date = builder.date;
        mark = builder.mark;
        courseId = builder.courseId;
        userId = builder.userId;
        userEntity = builder.userEntity;
        courseEntity = builder.courseEntity;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExamResultEntity)) {
            return false;
        }
        ExamResultEntity that = (ExamResultEntity) o;
        return id == that.id &&
                courseId == that.courseId &&
                userId == that.userId &&
                Objects.equals(date, that.date) &&
                Objects.equals(mark, that.mark) &&
                Objects.equals(userEntity, that.userEntity) &&
                Objects.equals(courseEntity, that.courseEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, mark, courseId, userId, userEntity, courseEntity);
    }

    public static final class Builder {
        private int id;
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

        public Builder withUser(UserEntity val) {
            userEntity = val;
            return this;
        }

        public Builder withCourse(CourseEntity val) {
            courseEntity = val;
            return this;
        }

        public ExamResultEntity build() {
            return new ExamResultEntity(this);
        }
    }
}
