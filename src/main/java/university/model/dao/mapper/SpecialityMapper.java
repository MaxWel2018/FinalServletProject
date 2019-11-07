package university.model.dao.mapper;

import university.model.dao.entity.SpecialityEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialityMapper {
    public static SpecialityEntity mapResultSetToSpeciality(ResultSet resultSet) throws SQLException {
        return SpecialityEntity.newBuilder()
                .withId(resultSet.getInt("Speciality_Id"))
                .withName(resultSet.getString("Speciality_Name"))
                .withStudentsNumber(resultSet.getInt("Students_Number"))
                .build();
    }
}
