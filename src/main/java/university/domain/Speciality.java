package university.domain;

import java.util.List;
import java.util.Objects;

public class Speciality {

    private Integer id;
    private String name;
    private Integer studentsNumber;

    private List<Course> requiredCourses;

    private Speciality(Builder builder) {
        id = builder.id;
        name = builder.name;
        studentsNumber = builder.studentsNumber;

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


    public List<Course> getRequiredCourses() {
        return requiredCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (!(o instanceof Speciality)){ return false;}
        Speciality that = (Speciality) o;
        return id.equals(that.id) &&
                studentsNumber.equals(that.studentsNumber) &&
                Objects.equals(name, that.name) &&
                Objects.equals(requiredCourses, that.requiredCourses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, studentsNumber, requiredCourses);
    }

    public static final class Builder {
        private int id;
        private String name;
        private int studentsNumber;
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



        public Builder withRequiredCourses(List<Course> val) {
            requiredCourses = val;
            return this;
        }

        public Speciality build() {
            return new Speciality(this);
        }
    }
}
