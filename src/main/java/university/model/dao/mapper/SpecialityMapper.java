package university.model.dao.mapper;

import university.model.dao.entity.SpecialityEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import static university.util.Convert.convertDateToLocalDate;

public class SpecialityMapper {
    public static SpecialityEntity mapResultSetToSpeciality(ResultSet resultSet) throws SQLException {
        return SpecialityEntity.newBuilder()
                .withId(resultSet.getInt("Speciality_Id"))
                .withName(resultSet.getString("Speciality_Name"))
                .withStudentsNumber(resultSet.getInt("Students_Number"))
                .withActivity(resultSet.getString("Activity"))
                .withBackground(resultSet.getString("Background"))
                .withEmployments(resultSet.getString("Employments"))
                .withExamsStart(convertDateToLocalDate(resultSet.getDate("Exams_Start")))
                .withExamsEnd(convertDateToLocalDate(resultSet.getDate("Exams_End")))
                .build();
    }

}
