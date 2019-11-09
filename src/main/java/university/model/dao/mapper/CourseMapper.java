package university.model.dao.mapper;

import university.model.dao.entity.CourseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CourseMapper {
    public static Optional<CourseEntity> mapResultSetToCourse(ResultSet resultSet) throws SQLException {
        return resultSet.next() ? Optional.ofNullable(new CourseEntity(resultSet.getInt("Course_Id"),
                resultSet.getString("Course_Name"))) : Optional.empty();


    }
//TODO сделать джинериком

}
