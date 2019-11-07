package university.model.dao.impl;

import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.contract.ResultForSpecialityDao;
import university.model.dao.entity.SpecialityRequestEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultForSpecialityDaoImpl extends AbstractCrudDaoImpl<SpecialityRequestEntity> implements ResultForSpecialityDao {
    private static final String FIND_BY_ID_QUERY = "SELECT *FROM result_for_speciality" +
            "INNER JOIN speciality s on result_for_speciality.Speciality_Id = s.Speciality_Id " +
            "WHERE Result_For_Speciality_ID=?";
    private static final String FIND_ALL_QUERY = "SELECT *FROM result_for_speciality " +
            "INNER JOIN speciality s on result_for_speciality.Speciality_Id = s.Speciality_Id";
    private static final String UPDATE_QUERY = "";
    private static final String DELETE_BY_ID = "";
    private static final String INSERT_RESULT = "";

    public ResultForSpecialityDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_RESULT, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID);
    }


    @Override
    protected SpecialityRequestEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, SpecialityRequestEntity entity) throws SQLException {

    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, SpecialityRequestEntity entity) throws SQLException {

    }
}
