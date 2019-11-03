package university.model.dao.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class ExamResult {

    private int id;
    private LocalDate date;
    private Integer mark;
    private int courseId;
    private int userId;
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


    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getMark() {
        return mark;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getUserId() {
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
        if (this == o) {return true;}
        if (!(o instanceof ExamResult)) {return false;}
        ExamResult that = (ExamResult) o;
        return id == that.id &&
                courseId == that.courseId &&
                userId == that.userId &&
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
        private int courseId;
        private int userId;
        private User user;
        private Course course;

        private Builder() {
        }

        public Builder withId(int val) {
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

        public Builder withCourseId(int val) {
            courseId = val;
            return this;
        }

        public Builder withUserId(int val) {
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
