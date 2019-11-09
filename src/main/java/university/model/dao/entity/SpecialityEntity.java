package university.model.dao.entity;

import java.util.Objects;

public class SpecialityEntity {

    private Integer id;
    private String name;
    private Integer studentsNumber;
    private String activity;
    private String background;
    private String employments;

    private SpecialityEntity(Builder builder) {
        id = builder.id;
        name = builder.name;
        studentsNumber = builder.studentsNumber;
        activity = builder.activity;
        background = builder.background;
        employments = builder.employments;
    }

    public static Builder newBuilder() {
        return new Builder();
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
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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

    public static final class Builder {
        private Integer id;
        private String name;
        private Integer studentsNumber;
        private String activity;
        private String background;
        private String employments;

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

        public SpecialityEntity build() {
            return new SpecialityEntity(this);
        }
    }
}
