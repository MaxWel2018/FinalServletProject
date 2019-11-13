package university.model.dao.mapper;

import university.model.dao.entity.CourseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper {
    public static CourseEntity mapResultSetToCourse(ResultSet resultSet) throws SQLException {
        return new CourseEntity(resultSet.getInt("Course_Id"),
                resultSet.getString("Course_Name"));


    }
//TODO сделать джинериком

}
