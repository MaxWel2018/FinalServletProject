package university.model.dao.mapper;

import university.model.dao.entity.Course;
import university.model.dao.exception.EntityNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseMapper {
    public static List<Course> mapResultSetToListCourse(ResultSet resultSet) throws SQLException {
        List<Course> courses = new ArrayList<>();
        while (resultSet.next()) {
            courses.add(mapResultSetToCourse(resultSet).orElseThrow( () ->
                    new EntityNotFoundException("Course not Found")));
        }
        return courses;

    }
    public static Optional<Course> mapResultSetToCourse(ResultSet resultSet) throws SQLException {
        return resultSet.next()?Optional.ofNullable(new Course(resultSet.getInt("Course_Id"), resultSet.getString("Course_Name"))):Optional.empty();
        //TODO Exception or Optional.empty ?

    }

}
