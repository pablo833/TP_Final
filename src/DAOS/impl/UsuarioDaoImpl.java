package DAOS.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAOS.UsuarioDAO;
import DB.DBManager;
import entidades.Usuario;
import exceptions.RadioException;

public class UsuarioDaoImpl implements UsuarioDAO {

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
	public void update(Usuario usuario) throws RadioException { // FIXME mirar tutorial jdbc con
																// preparedStatement

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
			throw new RadioException("Hubo un error en la actualizacion de usuarios", e);
		}

	}

	@Override
	public Usuario get(int codigo) throws RadioException {
		String query = "SELECT * FROM users where id = ?";

		Connection connection = DBManager.connect();

		PreparedStatement dml;
		try {
			dml = connection.prepareStatement(query);
			dml.setInt(1, codigo);

			executeQuery(connection, dml);
		} catch (SQLException e) {
			throw new RadioException("Hubo un error en la busqueda de usuarios", e);
		}

		return null;

	}

	public Usuario getByUserName(String userName) throws RadioException {

		Usuario user = null;

		String query = "select * from users where userName = ? ";

		Connection connection = DBManager.connect();

		try {
			PreparedStatement dml = connection.prepareStatement(query);

			dml.setString(1, userName);

			dml.executeQuery();
			ResultSet rs = dml.getResultSet();

			while (rs.next()) {
				user = new Usuario(rs.getInt("id"), rs.getString("userName"), rs.getString("password"),
						rs.getString("firstName"), rs.getString("lastName"));

			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new RadioException("Hubo un error en la busqueda de usuario", e1);
			}
			throw new RadioException("Hubo un error en la busqueda de usuario", e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e2) {
				throw new RadioException("Hubo un error en la busqueda de usuario", e2);
			}
		}
		return user;
	}

	@Override
	public List<Usuario> getAll() throws RadioException {

		List<Usuario> users = new ArrayList<Usuario>();

		String sql = "SELECT * FROM users";
		Connection c = DBManager.connect();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				Usuario user = new Usuario(rs.getInt("id"), rs.getString("userName"), rs.getString("password"),
						rs.getString("firstName"), rs.getString("lastName"));
				users.add(user);
			}
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				// no hago nada
			}
			throw new RadioException("Hubo un error en la busqueda de usuarios", e);
		} finally {
			try {
				c.close();

			} catch (SQLException e1) {
				throw new RadioException("Hubo un error en la busqueda de usuario", e1);
			}
		}
		return users;
	}

	@Override
	public void delete(String userName) throws RadioException {

		String query = "DELETE from users where userName = ?";
		Connection connection = DBManager.connect();

		PreparedStatement dml;
		try {
			dml = connection.prepareStatement(query);
			dml.setString(1, userName);

			executeQuery(connection, dml);
		} catch (SQLException e) {
			throw new RadioException("Hubo un error en la eliminacion de usuarios", e);
		}
	}

	private void executeQuery(Connection connection, PreparedStatement dml) throws RadioException {
		try {
			dml.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();

			} catch (SQLException e1) {
				// e1.printStackTrace();
			}
			throw new RadioException("Hubo un error en la ejecución a la BD", e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
				throw new RadioException("Hubo un error en la ejecución a la BD", e1);
			}
		}
	}

	@Override
	public void delete(int codigo) throws RadioException {
		// TODO Auto-generated method stub

	}
}
