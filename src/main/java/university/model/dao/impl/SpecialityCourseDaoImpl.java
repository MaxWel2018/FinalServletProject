package university.model.dao.impl;

import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.contract.SpecialityCourseDao;
import university.model.dao.entity.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialityCourseDaoImpl extends AbstractCrudDaoImpl<UserEntity> implements SpecialityCourseDao {
    private static final String FIND_BY_ID_QUERY = "";
    private static final String FIND_ALL_QUERY = "";
    private static final String UPDATE_QUERY = "";
    private static final String DELETE_BY_ID = "";
    private static final String INSERT_SPECIALITY_COURSES = "";


    public SpecialityCourseDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_SPECIALITY_COURSES, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID);
    }


    @Override
    protected UserEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, UserEntity entity) throws SQLException {

    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, UserEntity entity) throws SQLException {

    }
}
