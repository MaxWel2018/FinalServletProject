package university.model.dao.entity;

import java.util.Objects;

public class CourseEntity {

    private Integer id;
    private String name;

    public CourseEntity() {
    }

    public CourseEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseEntity course = (CourseEntity) o;
        return id.equals(course.id) &&
                Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
