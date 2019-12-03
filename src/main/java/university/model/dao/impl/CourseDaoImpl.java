package university.model.dao.impl;

import university.model.dao.CourseDao;
import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.entity.CourseEntity;
import university.model.dao.mapper.CourseEntityMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CourseDaoImpl extends AbstractCrudDaoImpl<CourseEntity> implements CourseDao {

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM course where Course_Id =?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM course ";
    private static final String UPDATE_QUERY = "UPDATE course SET Course_Name = ? WHERE  Course_Id = ?";
    private static final String INSERT_COURSE = "INSERT INTO exam_result(Date, Grade, Course_Id, User_Id)" +
            " VALUES (?,?,?,?)";
    private  final CourseEntityMapper courseEntityMapper;

    public CourseDaoImpl(HikariConnectionPool connector,CourseEntityMapper courseEntityMapper) {
        super(connector, INSERT_COURSE, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY);
        this.courseEntityMapper = courseEntityMapper;

    }

    @Override
    protected CourseEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return courseEntityMapper.mapResultSetToEntity(resultSet);
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, CourseEntity entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, CourseEntity entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(2, entity.getId());
    }
}
