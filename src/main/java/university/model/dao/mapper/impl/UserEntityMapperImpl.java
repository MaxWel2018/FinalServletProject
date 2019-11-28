package university.model.dao.mapper.impl;

import university.model.dao.entity.UserEntity;
import university.model.dao.mapper.RoleEntityMapper;
import university.model.dao.mapper.SpecialityEntityMapper;
import university.model.dao.mapper.UserEntityMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserEntityMapperImpl implements UserEntityMapper {

    private final SpecialityEntityMapper specialityEntityMapper;

    private final RoleEntityMapper roleEntityMapper;

    public UserEntityMapperImpl(SpecialityEntityMapper specialityEntityMapper, RoleEntityMapper roleEntityMapper) {
        this.specialityEntityMapper = specialityEntityMapper;
        this.roleEntityMapper = roleEntityMapper;
    }


    @Override
    public UserEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return UserEntity.newBuilder()
                .withId(resultSet.getInt("User_Id"))
                .withEmail(resultSet.getString("Email"))
                .withPassword(resultSet.getString("Password"))
                .withFirstName(resultSet.getString("First_Name"))
                .withSecondName(resultSet.getString("Second_Name"))
                .withSpecialityEntity(specialityEntityMapper.mapResultSetToEntity(resultSet))
                .withRole(roleEntityMapper.mapResultSetToEntity(resultSet))
                .build();
    }

}
