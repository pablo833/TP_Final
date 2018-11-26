package DAOS.impl;

import DAOS.DAO;
import DB.DBManager;
import ENTIDADES.Usuario;
import EXCEPTIONS.RadioException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoImpl extends AbstractImpl implements DAO<Usuario> {

    private final String USER_NOT_FOUND_ERRROR = "Hubo un error en la búsqueda de usuarios";

    @Override
    public void insert(Usuario usuario) throws RadioException {

        String query = "INSERT INTO users (userName, password, firstName, lastName ) VALUES (?, ?, ?, ?)";

        Connection connection = DBManager.connect();

        PreparedStatement dml;
        try {
            dml = connection.prepareStatement(query);
            dml.setString(1, usuario.getUserName());
            dml.setString(2, usuario.getPassword());
            dml.setString(3, usuario.getFirstName());
            dml.setString(4, usuario.getLastName());
            executeQuery(connection, dml);
        } catch (SQLException e) {
            throw new RadioException("Hubo un error en la insercion de usuarios", e);
        }
    }

    @Override
    public void update(Usuario usuario) throws RadioException {

        String query = "UPDATE users set userName = ?, firstName = ?, lastName = ? WHERE id = ?";

        Connection connection = DBManager.connect();

        try {
            PreparedStatement dml = connection.prepareStatement(query);

            dml.setString(1, usuario.getUserName());
            dml.setString(2, usuario.getFirstName());
            dml.setString(3, usuario.getLastName());
            dml.setInt(4, usuario.getCode());
            executeQuery(connection, dml);
        } catch (SQLException e) {
            throw new RadioException(USER_NOT_FOUND_ERRROR, e);
        }
    }

    @Override
    public Usuario get(int codigo) throws RadioException {
        Usuario user = null;

        String query = "select * from users where id = ? ";

        Connection connection = DBManager.connect();

        try {
            PreparedStatement dml = connection.prepareStatement(query);

            dml.setInt(1, codigo);

            dml.executeQuery();
            ResultSet rs = dml.getResultSet();

            while (rs.next()) {
                user = new Usuario(rs.getInt("id"),
                        rs.getString("userName"), rs.getString("password"),
                        rs.getString("firstName"), rs.getString("lastName"));

            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RadioException(USER_NOT_FOUND_ERRROR, e1);
            }
            throw new RadioException(USER_NOT_FOUND_ERRROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e2) {
                throw new RadioException(USER_NOT_FOUND_ERRROR, e2);
            }
        }
        return user;
    }

    @Override
    public void delete(int codigo) {

    }

    @Override
    public List<Usuario> getAll() throws RadioException {

        List<Usuario> users = new ArrayList<>();

        String query = "SELECT * FROM users";
        Connection connection = DBManager.connect();
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);

            while (rs.next()) {
                Usuario user = new Usuario(rs.getInt("id"),
                        rs.getString("userName"), rs.getString("password"),
                        rs.getString("firstName"), rs.getString("lastName"));
                users.add(user);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                // no hago nada
            }
            throw new RadioException(USER_NOT_FOUND_ERRROR, e);
        } finally {
            try {
                connection.close();

            } catch (SQLException e1) {
                throw new RadioException(USER_NOT_FOUND_ERRROR, e1);
            }
        }
        return users;
    }

    @Override
    public Usuario getByInternalID(Usuario usuario) throws RadioException {
        Usuario user = null;

        String query = "select * from users where userName = ? ";

        Connection connection = DBManager.connect();

        try {
            PreparedStatement dml = connection.prepareStatement(query);

            dml.setString(1, usuario.getUserName());

            dml.executeQuery();
            ResultSet rs = dml.getResultSet();

            while (rs.next()) {
                user = new Usuario(rs.getInt("id"),
                        rs.getString("userName"), rs.getString("password"),
                        rs.getString("firstName"), rs.getString("lastName"));

            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RadioException(USER_NOT_FOUND_ERRROR, e1);
            }
            throw new RadioException(USER_NOT_FOUND_ERRROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e2) {
                throw new RadioException(USER_NOT_FOUND_ERRROR, e2);
            }
        }
        return user;
    }
}
