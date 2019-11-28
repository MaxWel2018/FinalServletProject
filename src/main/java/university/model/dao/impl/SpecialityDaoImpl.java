package university.model.dao.impl;

import org.apache.log4j.Logger;
import university.model.dao.SpecialityDao;
import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.entity.CourseEntity;
import university.model.dao.entity.SpecialityEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Date.valueOf;

public class SpecialityDaoImpl extends AbstractCrudDaoImpl<SpecialityEntity> implements SpecialityDao {

    private static final String FIND_BY_ID_QUERY = "SELECT *" +
            "FROM speciality" +
            "         LEFT JOIN speciality_course sc on speciality.Speciality_Id = sc.Speciality_Id" +
            "         LEFT JOIN course c on sc.Course_Id = c.Course_Id " +
            "where sc.Speciality_Id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM speciality"
            + " LEFT JOIN speciality_course sc on speciality.Speciality_Id = sc.Speciality_Id" +
            "  LEFT JOIN course c on sc.Course_Id = c.Course_Id";
    private static final String UPDATE_QUERY = "UPDATE speciality " +
            "SET Speciality_Id = ?, Speciality_Name=?, Students_Number = ?,Activity = ?,Background = ?,Employments = ?," +
            "Exams_Start = ?,Exams_End= ? " +
            "where Speciality_Id = ?";
    private static final String INSERT_SPECIALITY = "INSERT " +
            "INTO speciality(SPECIALITY_NAME, STUDENTS_NUMBER, ACTIVITY, BACKGROUND, EMPLOYMENTS, EXAMS_START, EXAMS_END) " +
            "VALUES (?,?,?,?,?,?,?)";

    public SpecialityDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_SPECIALITY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY);
    }

    @Override
    protected SpecialityEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return SpecialityEntity.newBuilder()
                .withId(resultSet.getInt("Speciality_Id"))
                .withName(resultSet.getString("Speciality_Name"))
                .withStudentsNumber(resultSet.getInt("Students_Number"))
                .withActivity(resultSet.getString("Activity"))
                .withBackground(resultSet.getString("Background"))
                .withEmployments(resultSet.getString("Employments"))
                .withExamsStart((resultSet.getDate("Exams_Start")).toLocalDate())
                .withExamsEnd((resultSet.getDate("Exams_End")).toLocalDate())
                .withRequiredCourses(mapResultSetToSpecCourse(resultSet))
                .build();
    }

    @Override
    protected void mapForInsertStatement(PreparedStatement preparedStatement, SpecialityEntity entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setInt(2, entity.getStudentsNumber());
        preparedStatement.setString(3, entity.getActivity());
        preparedStatement.setString(4, entity.getBackground());
        preparedStatement.setString(5, entity.getEmployments());
        preparedStatement.setDate(6, valueOf(entity.getExamsStart()));
        preparedStatement.setDate(7, valueOf(entity.getExamsEnd()));
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, SpecialityEntity entity) throws SQLException {
        mapForInsertStatement(preparedStatement, entity);
        preparedStatement.setInt(8, entity.getId());
    }

    private CourseEntity mapResultSetToCourse(ResultSet resultSet) throws SQLException {
        return new CourseEntity(resultSet.getInt("Course_Id"),resultSet.getString("Course_Name"));
    }

    private List<CourseEntity> mapResultSetToSpecCourse(ResultSet resultSet) throws SQLException {
        List<CourseEntity> courses = new ArrayList<>();
        while (resultSet.next()) {
            courses.add(mapResultSetToCourse(resultSet));
        }
        return courses;
    }

}
