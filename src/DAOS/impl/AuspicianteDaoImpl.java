package DAOS.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAOS.DAO;
import DB.DBManager;
import ENTIDADES.Auspiciante;
import EXCEPTIONS.RadioException;

public class AuspicianteDaoImpl extends AbstractImpl implements DAO<Auspiciante> {
    private final String AUSPICIANTE_NOT_FOUND_ERRROR = "Hubo un error en la búsqueda de AUSPICIANTES";

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
            throw new RadioException(AUSPICIANTE_NOT_FOUND_ERRROR, e);
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
            throw new RadioException(AUSPICIANTE_NOT_FOUND_ERRROR, e);
        }

        return null;

    }

    @Override
    public void delete(int codigo) {

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
            throw new RadioException(AUSPICIANTE_NOT_FOUND_ERRROR, e);
        } finally {
            try {
                c.close();

            } catch (SQLException e1) {
                throw new RadioException(AUSPICIANTE_NOT_FOUND_ERRROR, e1);
            }
        }
        return auspiciantes;
    }

    @Override
    public Auspiciante getByInternalID(Auspiciante auspiciante) throws RadioException {
        Auspiciante newAuspiciante = null;

        String query = "select * from auspiciantes where razonSocial = ? ";

        Connection connection = DBManager.connect();

        try {
            PreparedStatement dml = connection.prepareStatement(query);

            dml.setString(1, auspiciante.getRazonSocial());

            dml.executeQuery();
            ResultSet rs = dml.getResultSet();

            while (rs.next()) {
                newAuspiciante = new Auspiciante(rs.getInt("id"), rs.getString("razonSocial"));

            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RadioException(AUSPICIANTE_NOT_FOUND_ERRROR, e1);
            }
            throw new RadioException(AUSPICIANTE_NOT_FOUND_ERRROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e2) {
                throw new RadioException(AUSPICIANTE_NOT_FOUND_ERRROR, e2);
            }
        }
        return newAuspiciante;
    }
}
