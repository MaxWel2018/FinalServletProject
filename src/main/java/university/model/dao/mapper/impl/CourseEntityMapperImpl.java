package university.model.dao.mapper.impl;

import university.model.dao.entity.CourseEntity;
import university.model.dao.mapper.CourseEntityMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseEntityMapperImpl implements CourseEntityMapper {
    @Override
    public CourseEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {

        return new CourseEntity(resultSet.getInt("Course_Id"),
                resultSet.getString("Course_Name"));
    }
}
