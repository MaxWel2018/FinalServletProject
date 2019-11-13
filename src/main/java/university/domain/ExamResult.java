package university.domain;

import java.time.LocalDate;
import java.util.Objects;

public class ExamResult {

    private Integer id;
    private LocalDate date;
    private Integer mark;
    private Integer courseId;
    private Integer userId;
    private User user;
    private Course course;

    private ExamResult(Builder builder) {
        id = builder.id;
        date = builder.date;
        mark = builder.mark;
        courseId = builder.courseId;
        userId = builder.userId;
        user = builder.user;
        course = builder.course;
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

    public User getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExamResult)) {
            return false;
        }
        ExamResult that = (ExamResult) o;
        return id.equals(that.id) &&
                courseId.equals(that.courseId) &&
                userId.equals(that.userId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(mark, that.mark) &&
                Objects.equals(user, that.user) &&
                Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, mark, courseId, userId, user, course);
    }

    public static final class Builder {
        private int id;
        private LocalDate date;
        private Integer mark;
        private Integer courseId;
        private Integer userId;
        private User user;
        private Course course;

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

        public Builder withUser(User val) {
            user = val;
            return this;
        }

        public Builder withCourse(Course val) {
            course = val;
            return this;
        }

        public ExamResult build() {
            return new ExamResult(this);
        }
    }
}
