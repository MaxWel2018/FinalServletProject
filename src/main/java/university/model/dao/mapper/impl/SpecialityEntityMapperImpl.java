package university.model.dao.mapper.impl;

import university.model.dao.entity.SpecialityEntity;
import university.model.dao.mapper.SpecialityEntityMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialityEntityMapperImpl implements SpecialityEntityMapper {
    @Override
    public SpecialityEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return resultSet.getObject("Id_Speciality") == null ? null : SpecialityEntity.newBuilder()
                .withId(resultSet.getInt("Speciality_Id"))
                .withName(resultSet.getString("Speciality_Name"))
                .withStudentsNumber(resultSet.getInt("Students_Number"))
                .withActivity(resultSet.getString("Activity"))
                .withBackground(resultSet.getString("Background"))
                .withEmployments(resultSet.getString("Employments"))
                .withExamsStart((resultSet.getDate("Exams_Start")).toLocalDate())
                .withExamsEnd((resultSet.getDate("Exams_End")).toLocalDate())
                .build();
    }
}
