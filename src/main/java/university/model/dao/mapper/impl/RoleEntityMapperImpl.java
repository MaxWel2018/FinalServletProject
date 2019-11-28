package university.model.dao.mapper.impl;

import university.model.dao.entity.Role;
import university.model.dao.mapper.RoleEntityMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleEntityMapperImpl implements RoleEntityMapper {
    @Override
    public Role mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Role.getRole(resultSet.getString("RoleName").toLowerCase());
    }
}
