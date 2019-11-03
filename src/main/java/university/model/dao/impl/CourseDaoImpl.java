package university.model.dao.impl;

import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.contract.CourseDao;
import university.model.dao.entity.Course;
import university.model.dao.exception.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static university.model.dao.mapper.CourseMapper.mapResultSetToCourse;

public class CourseDaoImpl extends AbstractCrudDaoImpl<Course> implements CourseDao {

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM course where Course_Id =?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM course ";
    private static final String UPDATE_QUERY = "UPDATE course SET Course_Name = ? WHERE  Course_Id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM course where  Course_Id = ?";
    private static final String INSERT_COURSE = "INSERT  INTO course(course_name) VALUES(?)";

    public CourseDaoImpl(HikariConnectionPool connector) throws SQLException {
        super(connector, INSERT_COURSE, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID);
    }


    @Override
    protected Course mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return mapResultSetToCourse(resultSet).orElseThrow(() -> new EntityNotFoundException("Course not Found"));
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Course entity) throws SQLException {
        preparedStatement.setString(1,entity.getName());
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, Course entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setInt(2, entity.getId());
    }
}
