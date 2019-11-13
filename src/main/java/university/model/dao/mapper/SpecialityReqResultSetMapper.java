package university.model.dao.mapper;

import university.model.dao.entity.SpecialityRequestEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialityReqResultSetMapper {
    public SpecialityRequestEntity mapResultSetToSpecialityReqEntity(ResultSet resultSet) throws SQLException {
        return SpecialityRequestEntity.newBuilder()
                .withId(resultSet.getInt("Result_For_Speciality_ID"))
                .withUserId(resultSet.getInt("User_Id"))
                .withUserName(resultSet.getString("First_Name"))
                .withUserSurName(resultSet.getString("Second_Name"))
                .withSpecialityId(resultSet.getInt("Speciality_Id"))
                .withSpecialityName(resultSet.getString("Speciality_Name"))
                .withFinalMark(resultSet.getInt("Final_Grade"))
                .withConfirmed(resultSet.getBoolean("Confirmed"))
                .build();
    }

}
