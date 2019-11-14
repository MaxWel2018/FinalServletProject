package university.model.dao.impl;

import university.model.dao.ExamResultDao;
import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.entity.ExamResultEntity;
import university.util.Convert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static university.util.Convert.convertLocaleDateToDate;

public class ExamResultDaoImpl extends AbstractCrudDaoImpl<ExamResultEntity> implements ExamResultDao {
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM  exam_result" +
            " LEFT JOIN course c on exam_result.Course_Id = c.Course_Id" +
            " RIGHT JOIN users u on exam_result.User_Id = u.User_Id" +
            " where Exam_Result_Id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM  exam_result " +
            "LEFT JOIN course c on exam_result.Course_Id = c.Course_Id" +
            " RIGHT JOIN users u on exam_result.User_Id = u.User_Id";
    private static final String UPDATE_QUERY = "UPDATE exam_result SET  Date =?, Grade = ?,Course_Id = ? , User_Id = ?" +
            " where Exam_Result_Id";
    private static final String INSERT_EXAM_RESULT = "INSERT INTO exam_result(Date, Grade, Course_Id, User_Id) VALUES (?,?,?,?)";

    public ExamResultDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_EXAM_RESULT, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY);
    }

    @Override
    protected ExamResultEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ExamResultEntity.newBuilder()
                .withId(resultSet.getInt("Exam_Result_Id"))
                .withDate(Convert.convertDateToLocalDate(resultSet.getDate("Date")))
                .withMark(resultSet.getInt("Grade"))
                .withCourseId(resultSet.getInt("Course_Id"))
                .withUserId(resultSet.getInt("User_Id"))
                .build();
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, ExamResultEntity entity) throws SQLException {
        preparedStatement.setDate(1, convertLocaleDateToDate(entity.getDate()));
        preparedStatement.setInt(2, getMark(entity));
        preparedStatement.setInt(3, entity.getCourseId());
        preparedStatement.setInt(4, entity.getUserId());
    }

    private Integer getMark(ExamResultEntity examResult) {
        return Optional.ofNullable(examResult)
                .map(ExamResultEntity::getMark)
                .orElse(0);
    }
    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, ExamResultEntity entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(5, entity.getId());
    }
}
