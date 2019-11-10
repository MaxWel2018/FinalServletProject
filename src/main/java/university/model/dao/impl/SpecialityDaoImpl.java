package university.model.dao.impl;

import org.apache.log4j.Logger;
import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.SpecialityDao;
import university.model.dao.entity.CourseEntity;
import university.model.dao.entity.SpecialityEntity;
import university.model.dao.exception.DataBaseRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static university.model.dao.mapper.SpecialityMapper.mapResultSetToSpeciality;

public class SpecialityDaoImpl extends AbstractCrudDaoImpl<SpecialityEntity> implements SpecialityDao {

    private static final Logger LOGGER = Logger.getLogger(SpecialityDaoImpl.class);

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM speciality " +
            " where Speciality_Id=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM speciality";
    private static final String UPDATE_QUERY = "UPDATE speciality  " +
            "SET Speciality_Name =?, Students_Number =?, Activity = ? ,Background = ? , Employments = ? " +
            "where Speciality_Id = ?";
    private static final String INSERT_SPECIALITY = "INSERT INTO speciality(speciality_name, students_number, activity, background, employments) " +
            "VALUES (?,?,?,?,?)";
    private static final String INSERT_TWO_PARAM_IN_SPECIALITY_COURSE = "INSERT INTO speciality_course(course_id, speciality_id) VALUES (?,?)";
    private static final String FIND_COURSES_FOR_SPECIALITY_BY_SPECIALITY_ID = "SELECT * FROM speciality_course " +
            "INNER JOIN course c on speciality_course.Course_Id = c.Course_Id" +
            " INNER JOIN speciality s on speciality_course.Speciality_Id = s.Speciality_Id" +
            " where s.Speciality_Id = ?";

    public SpecialityDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_SPECIALITY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY);
    }

    @Override
    protected SpecialityEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return mapResultSetToSpeciality(resultSet);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, SpecialityEntity entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setInt(2, entity.getStudentsNumber());
        preparedStatement.setString(3, entity.getActivity());
        preparedStatement.setString(4, entity.getBackground());
        preparedStatement.setString(5, entity.getEmployments());
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, SpecialityEntity entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setInt(6, entity.getId());
    }

    @Override
    public List<CourseEntity> getRequiredCoursesListBySpecId(Integer specialityId) {
        List<CourseEntity> cours = new ArrayList<>();

        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COURSES_FOR_SPECIALITY_BY_SPECIALITY_ID)) {
            preparedStatement.setInt(1,specialityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    cours.add(mapResultSetToCourse(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Insertion is failed", e);
            throw new DataBaseRuntimeException("Insertion is failed", e);
        }
        return cours;
    }

    private CourseEntity mapResultSetToCourse(ResultSet resultSet) throws SQLException {
        return new CourseEntity(resultSet.getInt("Course_Id"), resultSet.getString("Course_Name"));
    }


    @Override
    public void addCourse(Integer specialityId, Integer courseId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TWO_PARAM_IN_SPECIALITY_COURSE)) {
            preparedStatement.setInt(1, specialityId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Insertion is failed", e);
            throw new DataBaseRuntimeException("Insertion is failed", e);
        }

    }
}
