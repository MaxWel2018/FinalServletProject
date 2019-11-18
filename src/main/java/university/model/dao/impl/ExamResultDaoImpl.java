package university.model.dao.impl;

import org.apache.log4j.Logger;
import university.model.dao.ExamResultDao;
import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.entity.ExamResultEntity;
import university.model.dao.exception.DataBaseRuntimeException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExamResultDaoImpl extends AbstractCrudDaoImpl<ExamResultEntity> implements ExamResultDao {
    private static final Logger LOGGER = Logger.getLogger(ExamResultDaoImpl.class);

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM  exam_result" +
            " LEFT JOIN course c on exam_result.Id_Course = c.Course_Id" +
            " LEFT JOIN users u on exam_result.Id_User = u.User_Id" +
            " where Exam_Result_Id = ?";
    private static final String FIND_BY_USER_ID_AND_COURSE_ID_QUERY = "SELECT  * FROM exam_result where Id_User  =? AND  Id_Course=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM  exam_result " +
            "LEFT JOIN course c on exam_result.Id_Course = c.Course_Id " +
            "LEFT JOIN users u on exam_result.Id_User = u.User_Id";
    private static final String UPDATE_QUERY = "UPDATE exam_result SET  Date =?, Grade = ?,Id_Course = ? , Id_User = ?" +
            " where Exam_Result_Id";
    private static final String INSERT_EXAM_RESULT = "INSERT INTO exam_result(Date, Grade, Id_Course, Id_User) VALUES (?,?,?,?)";
    private static final String FIND_ALL_BY_COURSE_ID_AND_DATA = "SELECT * FROM exam_result " +
            " LEFT JOIN course c on exam_result.Id_Course = c.Course_Id " +
            "LEFT JOIN users u on exam_result.Id_User = u.User_Id" +
            " WHERE Id_Course=? AND Date = ?";

    public ExamResultDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_EXAM_RESULT, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY);
    }

    @Override
    protected ExamResultEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ExamResultEntity.newBuilder()
                .withId(resultSet.getInt("Exam_Result_Id"))
                .withDate(resultSet.getDate("Date").toLocalDate())
                .withMark(resultSet.getInt("Grade"))
                .withCourseId(resultSet.getInt("Id_Course"))
                .withUserId(resultSet.getInt("Id_User"))
                .build();
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, ExamResultEntity entity) throws SQLException {
        preparedStatement.setDate(1, Date.valueOf(entity.getDate()));
        preparedStatement.setObject(2, getMark(entity));
        preparedStatement.setInt(3, entity.getCourseId());
        preparedStatement.setInt(4, entity.getUserId());
    }

    private Object getMark(ExamResultEntity examResult) {
        return Optional.ofNullable(examResult)
                .map(ExamResultEntity::getMark)
                .orElse(null);
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, ExamResultEntity entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(5, entity.getId());
    }

    @Override
    public Optional<ExamResultEntity> findByUserIdAndCourseId(Integer userId, Integer courseId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_ID_AND_COURSE_ID_QUERY)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, courseId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return mapResultSetToExamResultEntity(userId, courseId, resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Finding  is failed", e);
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public List<ExamResultEntity> findAllByCourseIdAndData(Integer id, LocalDate date) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_COURSE_ID_AND_DATA)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setObject(2, Date.valueOf(date));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return mapResultSetToListExamResult(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Finding  is failed", e);
            throw new DataBaseRuntimeException(e);
        }
    }

    @Override
    public void updateGrade(Integer id, Integer grade) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE exam_result SET Grade = ? where Exam_Result_Id=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, grade);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Finding  is failed", e);
            throw new DataBaseRuntimeException(e);
        }
    }

    private List<ExamResultEntity> mapResultSetToListExamResult(ResultSet resultSet) throws SQLException {
        List<ExamResultEntity> examResultEntities = new ArrayList<>();
        while (resultSet.next()) {
            examResultEntities.add(
                    ExamResultEntity.newBuilder()
                            .withId(resultSet.getInt("Exam_Result_Id"))
                            .withDate((resultSet.getDate("Date")).toLocalDate())
                            .withMark(resultSet.getInt("Grade"))
                            .withCourseId(resultSet.getInt("Id_Course"))
                            .withUserId(resultSet.getInt("Id_User"))
                            .build());
        }
        return examResultEntities;
    }


    private Optional<ExamResultEntity> mapResultSetToExamResultEntity(Integer userId, Integer courseId, ResultSet resultSet) throws SQLException {
        return resultSet.next() ? Optional.of(ExamResultEntity.newBuilder()
                .withId(resultSet.getInt("Exam_Result_Id"))
                .withDate((resultSet.getDate("Date")).toLocalDate())
                .withUserId(userId)
                .withCourseId(courseId)
                .withMark((Integer) resultSet.getObject("Grade"))
                .build()) : Optional.empty();
    }


}
