package university.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;


public class Speciality {

    private Integer id;
    private final String name;
    private final Integer studentsNumber;
    private final String activity;
    private final String background;
    private final String employments;
    private final LocalDate examsStart;
    private final LocalDate examsEnd;
    private final List<Course> requiredCourses;

    private Speciality(Builder builder) {
        id = builder.id;
        name = builder.name;
        studentsNumber = builder.studentsNumber;
        activity = builder.activity;
        background = builder.background;
        employments = builder.employments;
        examsStart = builder.examsStart;
        examsEnd = builder.examsEnd;
        requiredCourses = builder.requiredCourses;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getStudentsNumber() {
        return studentsNumber;
    }

    public String getActivity() {
        return activity;
    }

    public String getBackground() {
        return background;
    }

    public String getEmployments() {
        return employments;
    }

    public LocalDate getExamsStart() {

        String formattedDate = examsStart.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
        return examsStart;
    }

    public LocalDate getExamsEnd() {
        return examsEnd;
    }

    public List<Course> getRequiredCourses() {
        return requiredCourses;
    }

    public static final class Builder {
        private Integer id;
        private String name;
        private Integer studentsNumber;
        private String activity;
        private String background;
        private String employments;
        private LocalDate examsStart;
        private LocalDate examsEnd;
        private List<Course> requiredCourses;

        private Builder() {
        }

        public Builder withId(Integer val) {
            id = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withStudentsNumber(Integer val) {
            studentsNumber = val;
            return this;
        }

        public Builder withActivity(String val) {
            activity = val;
            return this;
        }

        public Builder withBackground(String val) {
            background = val;
            return this;
        }

        public Builder withEmployments(String val) {
            employments = val;
            return this;
        }

        public Builder withExamsStart(LocalDate val) {
            examsStart = val;
            return this;
        }

        public Builder withExamsEnd(LocalDate val) {
            examsEnd = val;
            return this;
        }

        public Builder withRequiredCourses(List<Course> val) {
            requiredCourses = val;
            return this;
        }

        public Speciality build() {
            return new Speciality(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Speciality)) {
            return false;
        }
        Speciality that = (Speciality) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
