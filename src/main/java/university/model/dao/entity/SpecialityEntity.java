package university.model.dao.entity;

import java.util.List;
import java.util.Objects;

public class SpecialityEntity {

    private Integer id;
    private String name;
    private Integer studentsNumber;

    private List<CourseEntity> requiredCours;

    private SpecialityEntity(Builder builder) {
        id = builder.id;
        name = builder.name;
        studentsNumber = builder.studentsNumber;

        requiredCours = builder.requiredCours;
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


    public List<CourseEntity> getRequiredCours() {
        return requiredCours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SpecialityEntity)) {
            return false;
        }
        SpecialityEntity that = (SpecialityEntity) o;
        return id.equals(that.id) &&
                studentsNumber.equals(that.studentsNumber) &&
                Objects.equals(name, that.name) &&
                Objects.equals(requiredCours, that.requiredCours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, studentsNumber, requiredCours);
    }

    public static final class Builder {
        private int id;
        private String name;
        private int studentsNumber;
        private List<CourseEntity> requiredCours;

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


        public Builder withRequiredCourses(List<CourseEntity> val) {
            requiredCours = val;
            return this;
        }

        public SpecialityEntity build() {
            return new SpecialityEntity(this);
        }
    }
}
