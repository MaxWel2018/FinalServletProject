package university.model.dao.impl;

import university.model.dao.UserDao;
import university.model.dao.connection.HikariConnectionPool;
import university.model.dao.entity.Role;
import university.model.dao.entity.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl extends AbstractCrudDaoImpl<UserEntity> implements UserDao {
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users INNER JOIN role r on users.Role_Id = r.Role_Id WHERE User_Id=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM users INNER JOIN role r on users.Role_Id = r.Role_Id";
    private static final String UPDATE_QUERY = "UPDATE users" +
            " SET Email = ?,Password = ?, First_Name = ? ,Second_Name=?, Role_Id = ? WHERE  User_Id = ?";
    private static final String INSERT_USER = "INSERT INTO users(Email, Password, First_Name, Second_Name, Role_Id) VALUES(?,?,?,?,?)";
    private static final String FIND_BY_EMAIL = "SELECT * FROM users INNER JOIN role r on users.Role_Id = r.Role_Id where Email =?";


    public UserDaoImpl(HikariConnectionPool connector) {
        super(connector, INSERT_USER, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return findByStringParam(email, FIND_BY_EMAIL);
    }

    @Override
    protected UserEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return UserEntity.newBuilder()
                .withId(resultSet.getInt("User_Id"))
                .withEmail(resultSet.getString("Email"))
                .withPassword(resultSet.getString("Password"))
                .withFirstName(resultSet.getString("First_Name"))
                .withSecondName(resultSet.getString("Second_Name"))
                .withRole(mapResultSetToRole(resultSet))
                .build();
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, UserEntity entity) throws SQLException {
        preparedStatement.setString(1, entity.getEmail());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setString(3, entity.getFirstName());
        preparedStatement.setString(4, entity.getSecondName());
        preparedStatement.setInt(5, 1);
    }

    @Override
    protected void mapForUpdateStatement(PreparedStatement preparedStatement, UserEntity entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setInt(6, entity.getId());
    }

    private Role mapResultSetToRole(ResultSet resultSet) throws SQLException {
        return Role.getRole(resultSet.getString("RoleName").toLowerCase());
    }
}
