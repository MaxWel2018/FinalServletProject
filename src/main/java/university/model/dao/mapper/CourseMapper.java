package university.model.dao.mapper;

import university.model.dao.entity.CourseEntity;
import university.model.dao.exception.EntityNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseMapper {
    public static List<CourseEntity> mapResultSetToListCourse(ResultSet resultSet) throws SQLException {
        List<CourseEntity> cours = new ArrayList<>();
        while (resultSet.next()) {
            cours.add(mapResultSetToCourse(resultSet).orElseThrow(() ->
                    new EntityNotFoundException("Course not Found")));
        }
        return cours;

    }

    public static Optional<CourseEntity> mapResultSetToCourse(ResultSet resultSet) throws SQLException {
        return resultSet.next() ? Optional.ofNullable(new CourseEntity(resultSet.getInt("Course_Id"),
                resultSet.getString("Course_Name"))) : Optional.empty();


    }

}
