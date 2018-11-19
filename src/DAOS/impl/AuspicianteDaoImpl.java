package DAOS.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAOS.AuspicianteDAO;
import DB.DBManager;
import ENTIDADES.Auspiciante;
import EXCEPTIONS.RadioException;

public class AuspicianteDaoImpl implements AuspicianteDAO {

    @Override
    public void insert(Auspiciante auspiciante) throws RadioException {

        String query = "INSERT INTO auspiciantes (razonSocial) VALUES (?)";

        Connection connection = DBManager.connect();

        PreparedStatement dml;
        try {
            dml = connection.prepareStatement(query);
            dml.setString(1, auspiciante.getRazonSocial());
            executeQuery(connection, dml);
        } catch (SQLException e) {
            throw new RadioException("Hubo un error en la insercion de auspiciantes", e);
        }
    }

    @Override
    public void update(Auspiciante auspiciante) throws RadioException {

        String query = "UPDATE auspiciantes set razonSocial = ? WHERE id = ?";

        Connection connection = DBManager.connect();

        try {
            PreparedStatement dml = connection.prepareStatement(query);

            dml.setString(1, auspiciante.getRazonSocial());
            dml.setInt(2, auspiciante.getCode());
            executeQuery(connection, dml);
        } catch (SQLException e) {
            throw new RadioException("Hubo un error en la actualizacion de auspiciantes", e);
        }
    }

    @Override
    public Auspiciante get(int codigo) throws RadioException {
        String query = "SELECT * FROM auspiciantes where id = ?";

        Connection connection = DBManager.connect();

        PreparedStatement dml;
        try {
            dml = connection.prepareStatement(query);
            dml.setInt(1, codigo);

            executeQuery(connection, dml);
        } catch (SQLException e) {
            throw new RadioException("Hubo un error en la busqueda de auspiciantes", e);
        }

        return null;
    }

    @Override
    public void delete(int codigo) throws RadioException {

    }

    @Override
    public void delete(String razonSocial) throws RadioException {

    }

    @Override
    public List<Auspiciante> getAll() throws RadioException {
        List<Auspiciante> auspiciantes = new ArrayList<Auspiciante>();

        String sql = "SELECT * FROM auspiciantes";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Auspiciante auspiciante = new Auspiciante(rs.getInt("id"), rs.getString("razonSocial"));
                auspiciantes.add(auspiciante);
            }
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                // no hago nada
            }
            throw new RadioException("Hubo un error en la busqueda de auspiciantes", e);
        } finally {
            try {
                c.close();

            } catch (SQLException e1) {
                throw new RadioException("Hubo un error en la busqueda de auspiciantes", e1);
            }
        }
        return auspiciantes;
    }

    @Override
    public Auspiciante getByRazonSocial(String razonSocial) throws RadioException {
        Auspiciante auspiciante = null;

        String query = "select * from auspiciantes where razonSocial = ? ";

        Connection connection = DBManager.connect();

        try {
            PreparedStatement dml = connection.prepareStatement(query);

            dml.setString(1, razonSocial);

            dml.executeQuery();
            ResultSet rs = dml.getResultSet();

            while (rs.next()) {
                auspiciante = new Auspiciante(rs.getInt("id"), rs.getString("razonSocial"));

            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RadioException("Hubo un error en la busqueda de auspiciantes", e1);
            }
            throw new RadioException("Hubo un error en la busqueda de auspiciantes", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e2) {
                throw new RadioException("Hubo un error en la busqueda de auspiciantes", e2);
            }
        }
        return auspiciante;
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
            throw new RadioException("Hubo un error en la ejecucion a la BD", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e1) {
                throw new RadioException("Hubo un error en la ejecucion a la BD", e1);
            }
        }
    }

}
