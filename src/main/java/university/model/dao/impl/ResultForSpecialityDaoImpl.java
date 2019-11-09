package university.model.dao.impl;

import org.apache.log4j.Logger;
import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.contract.ResultForSpecialityDao;
import university.model.dao.entity.SpecialityRequestEntity;
import university.model.dao.exception.DataBaseRuntimeException;
import university.model.dao.mapper.SpecialityReqMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultForSpecialityDaoImpl extends AbstractCrudDaoImpl<SpecialityRequestEntity> implements ResultForSpecialityDao {

    private static final Logger LOGGER = Logger.getLogger(SpecialityDaoImpl.class);

    private static final String FIND_BY_ID_QUERY = "Select result_for_speciality.Final_Grade, result_for_speciality.Confirmed,  u.User_Id,u.First_Name,u.Second_Name,s.Speciality_Name,s.Speciality_Id " +
            "FROM result_for_speciality " +
            "INNER JOIN speciality s on result_for_speciality.Speciality_Id = s.Speciality_Id " +
            "INNER JOIN users u on result_for_speciality.User_Id = u.User_Id WHERE u.User_Id =?";

    private static final String FIND_ALL_QUERY = "Select * " +
            "FROM result_for_speciality " +
            "INNER JOIN speciality s on result_for_speciality.Speciality_Id = s.Speciality_Id " +
            "INNER JOIN users u on result_for_speciality.User_Id = u.User_Id LIMIT ?,?";
    private static final String FIND_ALL_QUERY_BY_ID_SPEC = "Select * FROM result_for_speciality INNER JOIN speciality s on result_for_speciality.Speciality_Id = s.Speciality_Id INNER JOIN users u on result_for_speciality.User_Id = u.User_Id WHERE s.Speciality_Id = ? LIMIT ?,?";
    private static final String UPDATE_QUERY = "UPDATE result_for_speciality SET Final_Grade=?, Speciality_Id = ?, User_Id = ?,Confirmed = ? WHERE Result_For_Speciality_ID=?";
    private static final String INSERT_RESULT = "INSERT  INTO result_for_speciality(final_grade, speciality_id, user_id,confirmed) VALUES (?,?,?,?)";
    private static final String SELECT_COUNT_FROM_RESULT_FOR_SPECIALITY_WHERE_SPECIALITY_ID = "SELECT Count(*) FROM result_for_speciality where Speciality_Id =?";
    private final SpecialityReqMapper specialityReqMapper;

    public ResultForSpecialityDaoImpl(HikariConnectionPool connector, SpecialityReqMapper reqMapper) {
        super(connector, INSERT_RESULT, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY);
        this.specialityReqMapper = reqMapper;
    }


    @Override
    protected SpecialityRequestEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return specialityReqMapper.mapResultSetToSpecialityReqEntity(resultSet);
    }


    @Override
    protected void insert(PreparedStatement preparedStatement, SpecialityRequestEntity entity) throws SQLException {
        preparedStatement.setInt(1, entity.getFinalMark());
        preparedStatement.setInt(2, entity.getSpecialityId());
        preparedStatement.setInt(3, entity.getUserId());
        preparedStatement.setBoolean(4, entity.getConfirmed());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, SpecialityRequestEntity entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setInt(5, entity.getId());
    }

    @Override
    public Integer countElementOfTableBySpecId(Integer idSpeciality) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNT_FROM_RESULT_FOR_SPECIALITY_WHERE_SPECIALITY_ID)) {
            preparedStatement.setInt(1, idSpeciality);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt(1) : 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Update is failed", e);
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public List<SpecialityRequestEntity> generateRating(Integer start, Integer recordsPerPage, Integer specialityId) {
        List<SpecialityRequestEntity> rating = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY_BY_ID_SPEC)) {
            preparedStatement.setInt(1, specialityId);
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, recordsPerPage);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    rating.add(specialityReqMapper.mapResultSetToSpecialityReqEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Insertion is failed", e);
            throw new DataBaseRuntimeException("Generate rating  is failed", e);
        }
        return rating;
    }
}
