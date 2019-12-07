package university.model.dao.impl;

import university.model.dao.SpecialityDao;
import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.entity.CourseEntity;
import university.model.dao.entity.SpecialityEntity;
import university.model.dao.mapper.CourseEntityMapper;

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

    private final CourseEntityMapper courseEntityMapper;


    public SpecialityDaoImpl(HikariConnectionPool connector, CourseEntityMapper courseEntityMapper) {
        super(connector, INSERT_SPECIALITY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY);
        this.courseEntityMapper = courseEntityMapper;
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
                .withRequiredCourses(mapResultSetToSpecCourse(resultSet,resultSet.getString("Speciality_Name")))
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

    private List<CourseEntity> mapResultSetToSpecCourse(ResultSet resultSet, String speciality_name) throws SQLException {
        List<CourseEntity> courses = new ArrayList<>();
        resultSet.beforeFirst();
        while (resultSet.next()) {
            if(resultSet.getString("Speciality_Name").equals(speciality_name))
            courses.add(courseEntityMapper.mapResultSetToEntity(resultSet));
        }
        return courses;
    }

}
