package university.model.dao.mapper;

import university.model.dao.entity.Speciality;

import java.sql.ResultSet;
import java.sql.SQLException;

import static university.model.dao.mapper.CourseMapper.mapResultSetToListCourse;

public class SpecialityMapper {
    public static Speciality mapResultSetToSpeciality(ResultSet resultSet) throws SQLException {
        return Speciality.newBuilder()
                .withId(resultSet.getInt("Speciality_Id"))
                .withName(resultSet.getString("Speciality_Name"))
                .withStudentsNumber(resultSet.getInt("Students_Number"))
                .build();
    }
}
