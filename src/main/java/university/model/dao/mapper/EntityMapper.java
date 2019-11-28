package university.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapper<E> {
    E mapResultSetToEntity(ResultSet resultSet) throws SQLException;
}
